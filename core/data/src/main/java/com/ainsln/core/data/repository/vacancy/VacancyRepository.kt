package com.ainsln.core.data.repository.vacancy

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.ShortVacancy
import kotlinx.coroutines.flow.Flow

interface VacancyRepository {
    fun getVacancies(): Flow<DataResult<List<ShortVacancy>>>
    fun getAllFavorites(): Flow<DataResult<List<ShortVacancy>>>
    fun getFavoritesNumber(): Flow<Int>
    suspend fun updateFavoriteStatus(vacancyId: String, isFavorite: Boolean): DataResult<Unit>
}

