package com.ainsln.core.network.retrofit

import com.ainsln.core.common.result.exception.HttpExceptionWrapper
import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.model.OfferDTO
import com.ainsln.core.network.model.ResponseDTO
import com.ainsln.core.network.model.VacancyDTO
import retrofit2.HttpException
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val retrofitApi: RetrofitApi
) : NetworkDataSource {

    override suspend fun getVacancies(): Result<List<VacancyDTO>> {
        return getData { it.vacancies }
    }

    override suspend fun getOffers(): Result<List<OfferDTO>> {
        return getData { it.offers }
    }

    override suspend fun getVacancyById(id: String): Result<VacancyDTO> {
        return try {
            getData { it.vacancies.first { v -> v.id == id } }
        } catch (e: Throwable){
            Result.failure(e)
        }
    }

    private suspend fun <T> getData(mapper: (ResponseDTO) -> T): Result<T> {
        return retrofitApi.get().onFailure { e ->
            throw when (e) {
                is HttpException -> HttpExceptionWrapper(e.code(), e.message, e)
                else -> e
            }
        }.map { mapper(it) }
    }
}
