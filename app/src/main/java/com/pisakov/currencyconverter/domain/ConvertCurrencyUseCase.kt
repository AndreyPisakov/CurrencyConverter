package com.pisakov.currencyconverter.domain

class ConvertCurrencyUseCase {
    fun convertCurrency(currencyAmount: Double, rate: Double): Double {
        return currencyAmount * rate
    }
}