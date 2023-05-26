package com.pisakov.common_impl

import android.content.Context
import com.pisakov.common.CommonUi
import com.pisakov.common.Core
import com.pisakov.common.CoreProvider
import com.pisakov.common.Logger
import com.pisakov.common.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
class CoreProviderModule {

    @Provides
    fun provideCoreProvider(
        @ApplicationContext context: Context,
    ): CoreProvider {
        return DefaultCoreProvider(
            appContext = context
        )
    }

    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return Core.globalScope
    }

    @Provides
    fun provideCommonUi(): CommonUi {
        return Core.commonUi
    }

    @Provides
    fun provideLogger(): Logger {
        return Core.logger
    }

    @Provides
    fun provideResources(): Resources {
        return Core.resources
    }

}