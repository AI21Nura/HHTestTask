package com.ainsln.core.common.di

import com.ainsln.core.common.asset.AssetManager
import com.ainsln.core.common.asset.BaseAssetManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Binds
    @Singleton
    fun bindAssetManager(
        manager: BaseAssetManager
    ): AssetManager

}
