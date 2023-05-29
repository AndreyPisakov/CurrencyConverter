package com.pisakov.data.currency.repositories

import com.pisakov.common.Logger
import com.pisakov.data.currency.sources.CurrencyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.net.UnknownHostException
import javax.inject.Inject

class CurrencyDataRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val logger: Logger
) : CurrencyDataRepository {

    private val _currencyFlow = MutableSharedFlow<Map<String, Double>>(1)
    val currencyFlow = _currencyFlow.asSharedFlow()

    override fun getCurrencyRates(): Flow<Map<String, Double>> {
        return currencyFlow
    }

    override suspend fun updateCurrencyRates() {
        try {
            val response = currencyApi.getCurrencyFromApi()
            _currencyFlow.emit(response.conversionRates)
        } catch (e: UnknownHostException) {
            logger.err(e, "unknown host")
        } catch (e: Exception) {
            logger.err(e, "api response not success")
        }
    }
}