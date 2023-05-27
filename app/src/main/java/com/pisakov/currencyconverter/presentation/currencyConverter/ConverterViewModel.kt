package com.pisakov.currencyconverter.presentation.currencyConverter

import androidx.lifecycle.ViewModel
import com.pisakov.currencyconverter.domain.currencyConverter.ConvertCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
): ViewModel() {

    fun validateCurrencyAmount(currencyAmount: String): Boolean {
        if (currencyAmount.isEmpty())
            return false
        return currencyAmount[0] != '.'
    }

    fun convertCurrency(currencyAmount: String, rate: Double): Double {
        return convertCurrencyUseCase.convertCurrency(currencyAmount.toDouble(), rate)
    }
}