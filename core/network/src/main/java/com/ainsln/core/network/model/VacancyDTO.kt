package com.ainsln.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class VacancyDTO(
    val id: String,
    val lookingNumber: Int? = null,
    val title: String,
    val address: AddressDTO? = null,
    val company: String,
    val experience: ExperienceDTO? = null,
    val publishedDate: String,
    val isFavorite: Boolean? = null,
    val salary: SalaryDTO? = null,
    val schedules: List<String>? = null,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>? = null
)

@Serializable
data class AddressDTO(
    val town: String,
    val street: String? = null,
    val house: String? = null
)

@Serializable
data class ExperienceDTO(
    val previewText: String? = null,
    val text: String? = null
)

@Serializable
data class SalaryDTO(
    val full: String,
    val short: String? = null
)
