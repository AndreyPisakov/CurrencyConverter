package com.pisakov.currencyconverter.domain.currencyConverter

import java.math.BigDecimal
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor() {
    fun convertCurrency(currencyAmount: Double, rate: Double): BigDecimal {
        return (Math.round(currencyAmount * rate * 100.0) / 100.0).toBigDecimal()
    }
}