package com.ainsln.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import androidx.room.Transaction
import com.ainsln.core.database.model.entity.ScheduleEntity
import com.ainsln.core.database.model.entity.DetailsScheduleCrossRef

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schedule: ScheduleEntity): Long

    @Query("SELECT id FROM schedules WHERE type = :scheduleType")
    suspend fun getIdByType(scheduleType: String): Long?

    @Transaction
    suspend fun getOrInsert(schedule: ScheduleEntity): Long {
        val existingId = getIdByType(schedule.scheduleType)
        return existingId ?: insert(schedule)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRelation(relation: DetailsScheduleCrossRef)
}
