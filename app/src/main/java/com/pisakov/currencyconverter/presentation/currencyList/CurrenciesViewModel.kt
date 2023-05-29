package com.pisakov.currencyconverter.presentation.currencyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisakov.currencyconverter.domain.currencyList.GetCurrenciesUseCase
import com.pisakov.currencyconverter.domain.entities.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    private var _currenciesStateFlow = MutableSharedFlow<List<Currency>>(1)
    val currenciesStateFlow = _currenciesStateFlow.asSharedFlow()

    init {
        getCurrenciesList()
        updateCurrencyRates()
    }

    private fun getCurrenciesList() {
        viewModelScope.launch {
            getCurrenciesUseCase.getCurrencies().collect {
                _currenciesStateFlow.emit(it)
            }
        }
    }

    fun updateCurrencyRates() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getCurrenciesUseCase.updateCurrencies()
            }
        }
    }
}