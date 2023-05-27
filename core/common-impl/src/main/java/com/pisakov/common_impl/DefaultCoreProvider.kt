package com.pisakov.common_impl

import com.pisakov.common.CoreProvider
import com.pisakov.common.Logger

class DefaultCoreProvider(
    override val logger: Logger = AndroidLogger()
) : CoreProvider