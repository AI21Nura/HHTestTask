package com.ainsln.core.database.di

import com.ainsln.core.database.VacancyLocalDataSource
import com.ainsln.core.database.room.RoomVacancyDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindRoomFavoritesDataSource(
        datasource: RoomVacancyDataSource
    ) : VacancyLocalDataSource

}
