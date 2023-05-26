package com.pisakov.common_impl

import android.content.Context
import android.widget.Toast
import com.pisakov.common.CommonUi

class AndroidCommonUi(private val applicationContext: Context) : CommonUi {

    override fun toast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}