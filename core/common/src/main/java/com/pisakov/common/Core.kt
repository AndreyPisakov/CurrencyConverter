package com.pisakov.common

object Core {

    private lateinit var coreProvider: CoreProvider

    val logger: Logger get() = coreProvider.logger

    val resources: Resources get() = coreProvider.resources

    fun init(coreProvider: CoreProvider) {
        this.coreProvider = coreProvider
    }

}