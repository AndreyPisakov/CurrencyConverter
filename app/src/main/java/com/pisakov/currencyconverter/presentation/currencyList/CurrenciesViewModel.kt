package com.pisakov.currencyconverter.presentation.currencyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisakov.currencyconverter.domain.GetCurrenciesUseCase
import com.pisakov.currencyconverter.domain.entities.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    init {
        updateCurrencyRates()
    }

    fun getCurrenciesList(): Flow<List<Currency>?> {
        return getCurrenciesUseCase.getCurrencies()
    }

    fun updateCurrencyRates() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getCurrenciesUseCase.updateCurrencies()
            }
        }
    }
}