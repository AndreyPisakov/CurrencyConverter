package com.pisakov.currencyconverter.domain.currencyList

import com.pisakov.currencyconverter.domain.entities.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencies(): Flow<List<Currency>>

    suspend fun updateRates()

}