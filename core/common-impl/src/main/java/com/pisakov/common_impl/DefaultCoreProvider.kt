package com.pisakov.common_impl

import android.content.Context
import com.pisakov.common.CoreProvider
import com.pisakov.common.Logger
import com.pisakov.common.Resources

class DefaultCoreProvider(
    private val appContext: Context,
    override val logger: Logger = AndroidLogger(),
    override val resources: Resources = AndroidResources(appContext)
) : CoreProvider