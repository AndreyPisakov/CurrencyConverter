package com.pisakov.currencyconverter

import android.app.Application
import com.pisakov.common.Core
import com.pisakov.common.CoreProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var coreProvider: CoreProvider

    override fun onCreate() {
        super.onCreate()
        instance = this
        Core.init(coreProvider)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}