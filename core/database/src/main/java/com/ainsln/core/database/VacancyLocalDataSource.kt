package com.ainsln.core.database

import com.ainsln.core.database.model.FullVacancy
import com.ainsln.core.database.model.entity.BaseVacancyEntity
import kotlinx.coroutines.flow.Flow

interface VacancyLocalDataSource {

    fun observeVacancies(): Flow<List<BaseVacancyEntity>>

    suspend fun getVacancies(): List<BaseVacancyEntity>

    suspend fun insertAll(baseVacancies: List<BaseVacancyEntity>)

    suspend fun insertDetails(vacancy: FullVacancy)

    fun getFavorites(): Flow<List<BaseVacancyEntity>>

    fun getFavoritesNumber(): Flow<Int>

    suspend fun updateFavoriteStatus(vacancyId: String, isFavorite: Boolean)

    suspend fun deleteDetailsById(id: String)
}
