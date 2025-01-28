package com.ainsln.core.network.retrofit

import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.model.OfferDTO
import com.ainsln.core.network.model.ResponseDTO
import com.ainsln.core.network.model.VacancyDTO
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(

    private val retrofitApi: RetrofitApi
) : NetworkDataSource {

    override suspend fun getVacancies(): Result<List<VacancyDTO>> {
        return getData{ it.vacancies }
    }

    override suspend fun getOffers(): Result<List<OfferDTO>> {
        return getData{ it.offers }
    }

    private suspend fun <T> getData(mapper: (ResponseDTO) -> T): Result<T>{
        val responseResult = retrofitApi.get()
        return responseResult.map { mapper(it) }
    }


}
