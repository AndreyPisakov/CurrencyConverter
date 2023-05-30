package com.pisakov.data.currency.repositories

import com.pisakov.data.currency.sources.CurrencyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

class CurrencyDataRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) : CurrencyDataRepository {

    private val _currencyFlow = MutableSharedFlow<Map<String, Double>>(1)
    val currencyFlow = _currencyFlow.asSharedFlow()

    override fun getCurrencyRates(): Flow<Map<String, Double>> {
        return currencyFlow
    }

    override suspend fun updateCurrencyRates() {
        val response = currencyApi.getCurrencyFromApi()
        var rates = response.conversionRates


        // !!!!! fake update rates !!!!!
        rates = rates.fakeUpdateRates()


        _currencyFlow.emit(rates)
    }





    /** !!!!! fake update rates !!!!! */
    var count = -1
    private fun Map<String, Double>.fakeUpdateRates(): Map<String, Double> {
        count++
        if (count == 0)
            return this
        return this.mapValues { element ->
            if (element.key == "RUB")
                return@mapValues element.value
            if (Random.nextBoolean()) {
                val start = element.value - element.value / 100 * count
                val end = element.value + element.value / 100 *  count
                val newValue = Random.nextDouble(start, end)
                (newValue * 1000000.0).roundToInt() / 1000000.0
            }
            else
                element.value
        }
    }
}