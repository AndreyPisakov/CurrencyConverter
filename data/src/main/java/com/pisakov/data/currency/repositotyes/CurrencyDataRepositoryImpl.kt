package com.pisakov.data.currency.repositotyes

import com.pisakov.common.Logger
import com.pisakov.data.currency.entities.CurrencyDataEntity
import com.pisakov.data.currency.sources.CurrencyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyDataRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val scope: CoroutineScope,
    private val logger: Logger
): CurrencyDataRepository {

    private val currencyFlow = MutableStateFlow<CurrencyDataEntity?>(null)

    override fun getCurrencyRates(): Flow<CurrencyDataEntity?> {
        return currencyFlow
    }

    override fun updateCurrencyRates() {
        scope.launch {
            val response = currencyApi.getCurrencyFromApi().execute()
            if (response.isSuccessful)
                currencyFlow.emit(response.body()!!.conversionRates)
            else
                logger.log("api response not success")
        }
    }
}