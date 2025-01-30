package com.ainsln.core.database.di

import android.content.Context
import androidx.room.Room
import com.ainsln.core.database.room.AppDatabase
import com.ainsln.core.database.room.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppRoomDatabase::class.java,
            "app_database.db"
        ).build()
    }

}
