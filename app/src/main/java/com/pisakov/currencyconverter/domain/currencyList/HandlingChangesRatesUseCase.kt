package com.pisakov.currencyconverter.domain.currencyList

import com.pisakov.currencyconverter.domain.LastCurrenciesList
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.currencyconverter.domain.entities.StateOfChangesRates
import javax.inject.Inject

class HandlingChangesRatesUseCase @Inject constructor() {
    fun handlingChangesRates(list : List<Currency>): List<Currency> {
        val oldList = LastCurrenciesList.lastList
        LastCurrenciesList.lastList = list
        if (oldList == null)
            return list
        return list.map { newCurrency ->
            val oldCurrency = oldList.find{ it.currencyCode == newCurrency.currencyCode }
            if (oldCurrency == null)
                newCurrency
            else if (oldCurrency.rate < newCurrency.rate)
                Currency(newCurrency.currencyCode, newCurrency.rate, StateOfChangesRates.INCREASED)
            else if (oldCurrency.rate > newCurrency.rate)
                Currency(newCurrency.currencyCode, newCurrency.rate, StateOfChangesRates.DECREASED)
            else  newCurrency
        }
    }
}