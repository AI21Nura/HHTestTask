package com.ainsln.core.database.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vacancy_details",
    foreignKeys = [
        ForeignKey(
            entity = BaseVacancyEntity::class,
            parentColumns = ["id"],
            childColumns = ["vacancy_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["vacancy_id"])]
)
data class VacancyDetailsEntity(
    @PrimaryKey
    @ColumnInfo("vacancy_id") val vacancyId: String,
    val description: String?,
    val responsibilities: String?,
    @ColumnInfo("applied_number") val appliedNumber: Int?,
    @ColumnInfo("salary_full") val salaryFull: String?,
    @ColumnInfo("experience_text") val experienceText: String?,
    @Embedded("address_") val address: AddressTuple?
)

data class AddressTuple(
    val street: String?,
    val house: String?
)
