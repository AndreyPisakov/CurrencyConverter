package com.pisakov.currencyconverter.domain

import com.pisakov.currencyconverter.domain.entities.Currency
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    fun getCurrencies(): Flow<List<Currency>?> {
        return currencyRepository.getCurrencies()
    }

    suspend fun updateCurrencies() {
        currencyRepository.updateRates()
    }
}