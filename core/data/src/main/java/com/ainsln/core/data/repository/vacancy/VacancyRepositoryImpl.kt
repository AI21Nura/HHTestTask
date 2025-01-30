package com.ainsln.core.data.repository.vacancy

import com.ainsln.core.common.di.IODispatcher
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.common.result.asFlowResult
import com.ainsln.core.common.result.exception.ExceptionHandler
import com.ainsln.core.data.mapper.VacancyMapper
import com.ainsln.core.database.VacancyLocalDataSource
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Логика работы с данными:
 * 1. При запросе вакансий данные загружаются параллельно из сети и кэша
 * 2. Приоритет отдается данным с сервера
 * 3. При успешном получении из сети:
 *    - Синхронизируются с кэшем статусы избранного
 *    - Обновленные данные сохраняются в кэш
 *    - Предполагается, что значения isFavorite для данных из сети могут быть
 *      неактуальными, так как отсутствует API для внесения изменений.
 * 4. При ошибке сети используются данные из кэша
 * 5. При ошибке и сети, и кэша возвращается ошибка
 *
 * Особенности хранения:
 * - В кэше хранятся базовые данные вакансий (ShortVacancy/BaseVacancyEntity)
 * - Для избранных вакансий дополнительно сохраняется полная информация (FullVacancy)
 */

internal class VacancyRepositoryImpl @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: VacancyLocalDataSource,
    private val exceptionHandler: ExceptionHandler,
    private val mapper: VacancyMapper
) : VacancyRepository {

    override suspend fun updateFavoriteStatus(
        vacancyId: String,
        isFavorite: Boolean
    ): DataResult<Unit> = withContext(dispatcher) {
        runCatching {
            localDataSource.updateFavoriteStatus(vacancyId, isFavorite)
            if (isFavorite) addToFavorites(vacancyId)
            else deleteFromFavorites(vacancyId)
        }.fold(
            onSuccess = { DataResult.Success(Unit) },
            onFailure = { DataResult.Failure(exceptionHandler.handle(it)) }
        )
    }

    override fun getAllFavorites(): Flow<DataResult<List<ShortVacancy>>> {
        return localDataSource.getFavorites()
            .map { list -> list.map { mapper.baseEntityToDomain(it) } }
            .asFlowResult(dispatcher, exceptionHandler::handle)
    }

    override fun getFavoritesNumber(): Flow<Int> {
        return localDataSource.getFavoritesNumber()
            .catch { e ->
                exceptionHandler.handle(e)
                emit(0)
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getVacancies(): Flow<DataResult<List<ShortVacancy>>> {
        return combineCacheAndServerData(getAllFromCache(), getAllFromServer())
            .flatMapConcat { result ->
                if (result is DataResult.Success){
                    localDataSource.observeVacancies()
                        .map { list -> list.map { mapper.baseEntityToDomain(it) } }
                        .asFlowResult(dispatcher, exceptionHandler::handle)
                } else
                    flowOf(result)
            }
    }

    private fun getAllFromServer(): Flow<DataResult<List<ShortVacancy>>> = flow {
        networkDataSource.getVacancies()
            .onSuccess { data -> emit(data.map { mapper.dtoToDomain(it) }) }
            .onFailure { throw it }
    }.asFlowResult(dispatcher, exceptionHandler::handle)

    private fun getAllFromCache(): Flow<DataResult<List<ShortVacancy>>> = flow {
        emit(localDataSource.getVacancies().map { mapper.baseEntityToDomain(it) })
    }.asFlowResult(dispatcher, exceptionHandler::handle)

    private fun combineCacheAndServerData(
        cacheFlow: Flow<DataResult<List<ShortVacancy>>>,
        serverFlow: Flow<DataResult<List<ShortVacancy>>>
    ): Flow<DataResult<List<ShortVacancy>>> {
        return combine(cacheFlow, serverFlow) { cacheResult, serverResult ->
            when {
                serverResult is DataResult.Success && cacheResult is DataResult.Success -> {
                    val syncData = syncVacanciesWithFavorites(cacheResult.data, serverResult.data)
                    try {
                        localDataSource.insertAll(syncData.map { mapper.domainToBaseEntity(it) })
                    } catch (e: Throwable){
                        exceptionHandler.handle(e)
                    }
                    DataResult.Success(syncData)
                }
                serverResult is DataResult.Failure && cacheResult is DataResult.Failure -> serverResult
                serverResult is DataResult.Failure -> cacheResult
                else -> serverResult
            }
        }
    }

    private fun syncVacanciesWithFavorites(
        cacheRes: List<ShortVacancy>,
        serverRes: List<ShortVacancy>
    ): List<ShortVacancy> {
        val favoritesIds = cacheRes.filter { it.isFavorite }.map { it.id }
        return serverRes.map { it.copy(isFavorite = favoritesIds.contains(it.id)) }
    }

    private suspend fun deleteFromFavorites(vacancyId: String) = withContext(dispatcher) {
        localDataSource.deleteDetailsById(vacancyId)
    }

    private suspend fun addToFavorites(vacancyId: String) = withContext(dispatcher) {
        val vacancyWithDetails = networkDataSource.getVacancyById(vacancyId).getOrThrow()
        localDataSource.insertDetails(mapper.dtoToFullEntity(vacancyWithDetails))
    }

}
