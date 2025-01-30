package com.ainsln.core.model

import java.util.Date

data class ShortVacancy(
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int?,
    val title: String,
    val salaryShort: String?,
    val town: String?,
    val company: String,
    val experiencePreview: String?,
    val publishedDate: Date
)
