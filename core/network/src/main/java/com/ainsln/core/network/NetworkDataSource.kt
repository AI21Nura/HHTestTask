package com.ainsln.core.network

import com.ainsln.core.network.model.OfferDTO
import com.ainsln.core.network.model.VacancyDTO

interface NetworkDataSource {
    suspend fun getVacancies(): Result<List<VacancyDTO>>
    suspend fun getOffers(): Result<List<OfferDTO>>
    suspend fun getVacancyById(id: String): Result<VacancyDTO>
}
