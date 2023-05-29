package com.pisakov.currencyconverter.domain.currencyConverter

import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor() {
    fun convertCurrency(currencyAmount: Double, rate: Double): String {
        return (Math.floor(currencyAmount * rate * 100.0) / 100.0).toBigDecimal().toPlainString()
    }
}