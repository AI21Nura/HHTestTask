package com.ainsln.core.database.room

import com.ainsln.core.database.VacancyLocalDataSource
import com.ainsln.core.database.model.FullVacancy
import com.ainsln.core.database.model.entity.BaseVacancyEntity
import com.ainsln.core.database.model.entity.DetailsScheduleCrossRef
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RoomVacancyDataSource @Inject constructor(
    database: AppDatabase
) : VacancyLocalDataSource {

    private val vacancyDao = database.favoritesDao()
    private val scheduleDao = database.scheduleDao()
    private val questionDao = database.questionDao()

    override fun observeVacancies(): Flow<List<BaseVacancyEntity>> {
        return vacancyDao.getAll()
    }

    override suspend fun getVacancies(): List<BaseVacancyEntity> {
        return vacancyDao.getList()
    }

    override suspend fun insertAll(baseVacancies: List<BaseVacancyEntity>) {
        return vacancyDao.insertBaseList(baseVacancies)
    }

    override suspend fun insertDetails(vacancy: FullVacancy) {
        try {
            val vacancyId = vacancy.baseVacancy.id
            vacancyDao.insertDetails(vacancy.details)
            vacancy.schedules.forEach { schedule ->
                val scheduleId = scheduleDao.getOrInsert(schedule)
                scheduleDao.insertRelation(
                    DetailsScheduleCrossRef(vacancyId, scheduleId)
                )
            }
            questionDao.insert(
                vacancy.questions.map { it.copy(detailsId = vacancyId) }
            )
        } catch (e: Throwable){
            throw e
        }
    }

    override fun getFavorites(): Flow<List<BaseVacancyEntity>> {
        return vacancyDao.getFavorites()
    }

    override fun getFavoritesNumber(): Flow<Int> {
        return vacancyDao.getFavoritesCount()
    }

    override suspend fun updateFavoriteStatus(vacancyId: String, isFavorite: Boolean) {
        vacancyDao.updateFavoriteStatus(vacancyId, isFavorite)
    }

    override suspend fun deleteDetailsById(id: String) {
        vacancyDao.deleteDetailsById(id)
    }
}
