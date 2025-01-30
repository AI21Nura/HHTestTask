package com.ainsln.core.database.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "schedules",
    indices = [Index(value = ["type"], unique = true)]
)
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("type") val scheduleType: String
)

