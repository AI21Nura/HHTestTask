package com.ainsln.core.database.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = VacancyDetailsEntity::class,
            parentColumns = ["vacancy_id"],
            childColumns = ["details_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["details_id"])]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("details_id") val detailsId: String,
    @ColumnInfo("question_text") val questionText: String
)
