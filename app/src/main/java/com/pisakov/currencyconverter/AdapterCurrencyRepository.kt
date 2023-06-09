package com.pisakov.currencyconverter

import com.pisakov.currencyconverter.domain.currencyList.CurrencyRepository
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.data.currency.repositories.CurrencyDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCurrencyRepository @Inject constructor(
    private val currencyDataRepository: CurrencyDataRepository
): CurrencyRepository {

    override fun getCurrencies(): Flow<List<Currency>> {
        return currencyDataRepository.getCurrencyRates().map { mapCurrency ->
            mapCurrency.map { Currency(it.key, it.value) }
        }
    }

    override suspend fun updateRates() {
        currencyDataRepository.updateCurrencyRates()
    }
}