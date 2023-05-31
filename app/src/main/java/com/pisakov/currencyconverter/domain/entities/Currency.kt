package com.pisakov.currencyconverter.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val currencyCode: String,
    val rate: Double,
    val stateOfChangesRates: StateOfChangesRates = StateOfChangesRates.WITHOUT_CHANGES
): Parcelable

enum class StateOfChangesRates { INCREASED, DECREASED, WITHOUT_CHANGES }
