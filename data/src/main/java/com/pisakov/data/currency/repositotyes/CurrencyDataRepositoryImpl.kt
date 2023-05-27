package com.pisakov.data.currency.repositotyes

import com.pisakov.common.Logger
import com.pisakov.data.currency.sources.CurrencyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CurrencyDataRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val logger: Logger
) : CurrencyDataRepository {

    private val currencyFlow = MutableStateFlow<Map<String, Double>?>(null)

    override fun getCurrencyRates(): StateFlow<Map<String, Double>?> {
        return currencyFlow
    }

    override suspend fun updateCurrencyRates() {
        val response = currencyApi.getCurrencyFromApi().execute()
        if (response.isSuccessful)
            currencyFlow.emit(response.body()!!.conversionRates)
        else
            logger.log("api response not success")
    }
}