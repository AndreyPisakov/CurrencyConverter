package com.pisakov.currencyconverter.presentation.currencyconverter

import androidx.lifecycle.ViewModel
import com.pisakov.currencyconverter.domain.ConvertCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
): ViewModel() {

    fun convertCurrency(currencyAmount: String, rate: Double): Double {
        return convertCurrencyUseCase.convertCurrency(currencyAmount.toDouble(), rate)
    }
}