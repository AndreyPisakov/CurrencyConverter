package com.pisakov.common

object Core {

    private lateinit var coreProvider: CoreProvider

    val logger: Logger get() = coreProvider.logger

    fun init(coreProvider: CoreProvider) {
        this.coreProvider = coreProvider
    }

}