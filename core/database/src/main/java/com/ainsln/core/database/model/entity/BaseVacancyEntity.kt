package com.ainsln.core.database.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "vacancies")
data class BaseVacancyEntity(
    @PrimaryKey val id: String,
    val title: String,
    val company: String,
    @ColumnInfo("is_favorite") val isFavorite: Boolean,
    @ColumnInfo("looking_number") val lookingNumber: Int?,
    @ColumnInfo("published_date") val publishedDate: Date,
    @ColumnInfo("salary_short") val salaryShort: String?,
    @ColumnInfo("address_town") val town: String?,
    @ColumnInfo("experience_preview") val experiencePreview: String?
)


