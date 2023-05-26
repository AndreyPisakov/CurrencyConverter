package com.pisakov.common

import kotlinx.coroutines.CoroutineScope

interface CoreProvider {

    val commonUi: CommonUi

    val logger: Logger

    val resources: Resources

    val globalScope: CoroutineScope

}