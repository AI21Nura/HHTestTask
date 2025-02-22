package com.ainsln.core.network.di

import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.fake.FakeNetworkDataSource
import com.ainsln.core.network.retrofit.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindsNetworkDataSource(
        dataSource: RetrofitDataSource
    ) : NetworkDataSource

    @Binds
    @Singleton
    @Named("FakeNetwork")
    fun bindsFakeNetworkDataSource(
        dataSource: FakeNetworkDataSource
    ) : NetworkDataSource

}
