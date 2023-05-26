package com.pisakov.common_impl

import android.content.Context
import com.pisakov.common.CommonUi
import com.pisakov.common.CoreProvider
import com.pisakov.common.Logger
import com.pisakov.common.Resources
import kotlinx.coroutines.CoroutineScope

class DefaultCoreProvider(
    private val appContext: Context,
    override val commonUi: CommonUi = AndroidCommonUi(appContext),
    override val resources: Resources = AndroidResources(appContext),
    override val globalScope: CoroutineScope = createDefaultGlobalScope(),
    override val logger: Logger = AndroidLogger(),
) : CoreProvider