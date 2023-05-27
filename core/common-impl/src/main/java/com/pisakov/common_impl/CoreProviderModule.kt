package com.pisakov.common_impl

import com.pisakov.common.Core
import com.pisakov.common.CoreProvider
import com.pisakov.common.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoreProviderModule {

    @Provides
    fun provideCoreProvider(): CoreProvider {
        return DefaultCoreProvider()
    }

    @Provides
    fun provideLogger(): Logger {
        return Core.logger
    }

}