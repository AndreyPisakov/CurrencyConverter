package com.pisakov.common

import kotlinx.coroutines.CoroutineScope

object Core {

    private lateinit var coreProvider: CoreProvider

    val commonUi: CommonUi get() = coreProvider.commonUi

    val resources: Resources get() = coreProvider.resources

    val logger: Logger get() = coreProvider.logger

    val globalScope: CoroutineScope get() = coreProvider.globalScope

    fun init(coreProvider: CoreProvider) {
        this.coreProvider = coreProvider
    }

}