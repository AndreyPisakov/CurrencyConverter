package com.pisakov.currencyconverter.domain.currencyConverter

import javax.inject.Inject
import kotlin.math.floor

class ConvertCurrencyUseCase @Inject constructor() {
    fun convertCurrency(currencyAmount: Double, rate: Double): String {
        return (floor(currencyAmount * rate * 100.0) / 100.0).toBigDecimal().toPlainString()
    }
}