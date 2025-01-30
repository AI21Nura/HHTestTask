package com.ainsln.core.network.fake

import com.ainsln.core.common.asset.AssetManager
import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.model.OfferDTO
import com.ainsln.core.network.model.ResponseDTO
import com.ainsln.core.network.model.VacancyDTO
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    private val assetManager: AssetManager,
    private val networkJson: Json
) : NetworkDataSource {

    override suspend fun getVacancies(): Result<List<VacancyDTO>> {
        return getResult { it.vacancies }
    }

    override suspend fun getOffers(): Result<List<OfferDTO>> {
        return getResult { it.offers }
    }

    override suspend fun getVacancyById(id: String): Result<VacancyDTO> {
        return try {
            getResult { it.vacancies.first { v -> v.id == id } }
        } catch (e: Throwable){
            Result.failure(e)
        }
    }

    private fun <T> getResult(mapper: (ResponseDTO) -> T): Result<T> {
        return try {
            Result.success(mapper(getData()))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getData(): ResponseDTO {
        return assetManager.open("mock.json").use {
            networkJson.decodeFromStream(it)
        }
    }
}
