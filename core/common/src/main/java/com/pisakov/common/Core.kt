package com.pisakov.common

object Core {

    private lateinit var coreProvider: CoreProvider

    val commonUi: CommonUi get() = coreProvider.commonUi

    val resources: Resources get() = coreProvider.resources

    val logger: Logger get() = coreProvider.logger

    fun init(coreProvider: CoreProvider) {
        this.coreProvider = coreProvider
    }

}