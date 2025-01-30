package com.ainsln.core.data.di

import com.ainsln.core.data.repository.offer.OfferRepository
import com.ainsln.core.data.repository.offer.OfferRepositoryImpl
import com.ainsln.core.data.repository.vacancy.VacancyRepository
import com.ainsln.core.data.repository.vacancy.VacancyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindVacancyRepository(
        repository: VacancyRepositoryImpl
    ): VacancyRepository

    @Binds
    @Singleton
    fun bindOfferRepository(
        repository: OfferRepositoryImpl
    ): OfferRepository

}
