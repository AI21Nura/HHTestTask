package com.ainsln.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ainsln.core.database.model.entity.VacancyDetailsEntity
import com.ainsln.core.database.model.entity.BaseVacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {

    @Query("SELECT * FROM vacancies")
    suspend fun getList(): List<BaseVacancyEntity>

    @Query("SELECT * FROM vacancies")
    fun getAll(): Flow<List<BaseVacancyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBaseList(vacancies: List<BaseVacancyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBase(vacancy: BaseVacancyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(vacancy: VacancyDetailsEntity)

    @Query("SELECT * FROM vacancies WHERE is_favorite=1")
    fun getFavorites(): Flow<List<BaseVacancyEntity>>

    @Query("SELECT COUNT(*) FROM vacancies WHERE is_favorite=1")
    fun getFavoritesCount(): Flow<Int>

    @Query("UPDATE vacancies SET is_favorite=:isFavorite WHERE id=:vacancyId")
    suspend fun updateFavoriteStatus(vacancyId: String, isFavorite: Boolean)

    @Query("DELETE FROM vacancy_details WHERE vacancy_id=:vacancyId")
    suspend fun deleteDetailsById(vacancyId: String)
}
