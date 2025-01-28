package com.ainsln.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO(
    val offers: List<OfferDTO>,
    val vacancies: List<VacancyDTO>
)
