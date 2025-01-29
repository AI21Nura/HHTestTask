package com.ainsln.core.common.di

import com.ainsln.core.common.asset.AssetManager
import com.ainsln.core.common.asset.BaseAssetManager
import com.ainsln.core.common.result.exception.BaseExceptionHandler
import com.ainsln.core.common.result.exception.ExceptionHandler
import com.ainsln.core.common.utils.AndroidLogcatLogger
import com.ainsln.core.common.utils.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CommonModule {

    @Binds
    @Singleton
    fun bindAssetManager(
        manager: BaseAssetManager
    ): AssetManager

    @Binds
    @Singleton
    fun bindLogger(
        logger: AndroidLogcatLogger
    ): Logger

    @Binds
    @Singleton
    fun bindExceptionHandler(
        handler: BaseExceptionHandler
    ): ExceptionHandler

}
