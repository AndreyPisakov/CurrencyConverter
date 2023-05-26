package com.pisakov.currencyconverter.domain

import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor() {
    fun convertCurrency(currencyAmount: Double, rate: Double): Double {
        return currencyAmount * rate
    }
}