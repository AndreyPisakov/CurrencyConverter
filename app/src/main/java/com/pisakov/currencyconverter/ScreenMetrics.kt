package com.pisakov.currencyconverter

import android.content.res.Resources

object ScreenMetrics {
    private const val padding = 24
    var screenWidth: Int = getScreenWidthPx()
    var dpToPx = getPaddingPx()

    fun getScreenWidthPx(): Int = Resources.getSystem().displayMetrics.widthPixels
    fun getPaddingPx(): Int = (Resources.getSystem().displayMetrics.density * padding).toInt()
}