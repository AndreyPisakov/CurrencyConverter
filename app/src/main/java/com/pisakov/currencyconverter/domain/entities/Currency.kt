package com.pisakov.currencyconverter.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val currencyCode: String,
    val rate: Double
): Parcelable
