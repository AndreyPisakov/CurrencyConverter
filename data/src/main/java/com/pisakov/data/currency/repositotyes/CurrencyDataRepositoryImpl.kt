package com.pisakov.data.currency.repositotyes

import com.pisakov.common.Logger
import com.pisakov.data.currency.sources.CurrencyApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.net.UnknownHostException
import javax.inject.Inject

class CurrencyDataRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val logger: Logger
) : CurrencyDataRepository {

    private val currencyFlow = MutableSharedFlow<Map<String, Double>?>(1)

    override fun getCurrencyRates(): SharedFlow<Map<String, Double>?> {
        return currencyFlow
    }

    override suspend fun updateCurrencyRates() {
        try {
            val response = currencyApi.getCurrencyFromApi().execute()
            currencyFlow.emit(response.body()!!.conversionRates)
        } catch (e: UnknownHostException) {
            logger.err(e, "unknown host")
        } catch (e: Exception) {
            logger.err(e, "api response not success")
        }
    }
}