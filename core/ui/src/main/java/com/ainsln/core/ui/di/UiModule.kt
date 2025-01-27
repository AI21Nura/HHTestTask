package com.ainsln.core.ui.di

import com.ainsln.core.ui.utils.BaseIntentManager
import com.ainsln.core.ui.utils.IntentManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UiModule{

    @Binds
    @Singleton
    fun bindIntentManager(
        manager: BaseIntentManager
    ) : IntentManager

}
