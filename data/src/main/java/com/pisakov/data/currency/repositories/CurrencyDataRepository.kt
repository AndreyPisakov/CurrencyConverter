package com.pisakov.data.currency.repositories

import kotlinx.coroutines.flow.Flow

interface CurrencyDataRepository {

    fun getCurrencyRates(): Flow<Map<String, Double>>

    suspend fun updateCurrencyRates()
}