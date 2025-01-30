package com.ainsln.core.database.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "details_schedules",
    primaryKeys = ["details_id", "schedule_id"],
    foreignKeys = [
        ForeignKey(
            entity = VacancyDetailsEntity::class,
            parentColumns = ["vacancy_id"],
            childColumns = ["details_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ScheduleEntity::class,
            parentColumns = ["id"],
            childColumns = ["schedule_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index(value = ["details_id"]),
        Index(value = ["schedule_id"])
    ]
)
data class DetailsScheduleCrossRef(
    @ColumnInfo("details_id") val detailsId: String,
    @ColumnInfo("schedule_id") val scheduleId: Long
)
