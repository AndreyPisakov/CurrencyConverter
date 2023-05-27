package com.pisakov.data.currency.repositotyes

import kotlinx.coroutines.flow.Flow

interface CurrencyDataRepository {

    fun getCurrencyRates(): Flow<Map<String, Double>?>

    suspend fun updateCurrencyRates()
}