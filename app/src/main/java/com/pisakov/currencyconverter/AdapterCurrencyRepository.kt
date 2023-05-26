package com.pisakov.currencyconverter

import com.pisakov.currencyconverter.ConvertDataClass.serializeToMap
import com.pisakov.currencyconverter.domain.CurrencyRepository
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.data.currency.repositotyes.CurrencyDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCurrencyRepository @Inject constructor(
    private val currencyDataRepository: CurrencyDataRepository,
): CurrencyRepository {

    override fun getCurrencies(): Flow<List<Currency>> {
        return currencyDataRepository.getCurrencyRates().map {
            it.serializeToMap()
        }
    }
}