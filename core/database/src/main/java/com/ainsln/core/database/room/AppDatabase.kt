package com.ainsln.core.database.room

import com.ainsln.core.database.dao.VacancyDao
import com.ainsln.core.database.dao.QuestionDao
import com.ainsln.core.database.dao.ScheduleDao

interface AppDatabase {
    fun favoritesDao(): VacancyDao
    fun scheduleDao(): ScheduleDao
    fun questionDao(): QuestionDao
}
