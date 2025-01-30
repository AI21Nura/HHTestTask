package com.ainsln.core.data.di

import com.ainsln.core.data.mapper.OfferMapper
import com.ainsln.core.data.mapper.OfferMapperImpl
import com.ainsln.core.data.mapper.VacancyMapper
import com.ainsln.core.data.mapper.VacancyMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    @Singleton
    fun bindVacancyMapper(
        mapper: VacancyMapperImpl
    ): VacancyMapper

    @Binds
    @Singleton
    fun bindOfferMapper(
        mapper: OfferMapperImpl
    ): OfferMapper

}
