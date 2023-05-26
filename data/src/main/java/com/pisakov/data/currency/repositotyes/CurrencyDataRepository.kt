package com.pisakov.data.currency.repositotyes

import com.pisakov.data.currency.entities.CurrencyDataEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDataRepository {

    fun getCurrencyRates(): Flow<CurrencyDataEntity?>

    fun updateCurrencyRates()
}