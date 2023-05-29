package com.pisakov.common_impl

import android.content.Context
import com.pisakov.common.Resources

class AndroidResources (private val applicationContext: Context) : Resources {
    override fun getString(id: Int): String {
        return applicationContext.getString(id)
    }
}