package com.pisakov.currencyconverter.presentation

import android.content.res.Resources

object ScreenMetrics {
    private const val padding = 24
    const val SPAN_COUNT = 2

    var screenWidth: Int = getScreenWidthPx()
    var dpToPx = getPaddingPx()

    fun getScreenWidthPx(): Int = Resources.getSystem().displayMetrics.widthPixels
    fun getPaddingPx(): Int = (Resources.getSystem().displayMetrics.density * padding).toInt()
}