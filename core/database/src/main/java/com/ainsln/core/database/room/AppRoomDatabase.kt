package com.ainsln.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ainsln.core.database.dao.VacancyDao
import com.ainsln.core.database.dao.QuestionDao
import com.ainsln.core.database.dao.ScheduleDao
import com.ainsln.core.database.model.entity.QuestionEntity
import com.ainsln.core.database.model.entity.ScheduleEntity
import com.ainsln.core.database.model.entity.BaseVacancyEntity
import com.ainsln.core.database.model.entity.DetailsScheduleCrossRef
import com.ainsln.core.database.model.entity.VacancyDetailsEntity
import com.ainsln.core.database.utils.DateConverter

@Database(
    version = 1,
    entities = [
        BaseVacancyEntity::class,
        VacancyDetailsEntity::class,
        ScheduleEntity::class,
        QuestionEntity::class,
        DetailsScheduleCrossRef::class
    ]
)
@TypeConverters(DateConverter::class)
internal abstract class AppRoomDatabase : RoomDatabase(), AppDatabase {
    abstract override fun favoritesDao(): VacancyDao
    abstract override fun scheduleDao(): ScheduleDao
    abstract override fun questionDao(): QuestionDao
}

