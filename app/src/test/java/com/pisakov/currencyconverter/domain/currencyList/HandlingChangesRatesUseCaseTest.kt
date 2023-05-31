package com.pisakov.currencyconverter.domain.currencyList

import com.pisakov.currencyconverter.domain.LastCurrenciesList
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.currencyconverter.domain.entities.StateOfChangesRates
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class HandlingChangesRatesUseCaseTest {

    @Test
    fun `handlingChangesRates() updates LastCurrenciesList`() {
        mockkObject(LastCurrenciesList)
        val handlingChangesRatesUseCase = HandlingChangesRatesUseCase()
        val list = listOf<Currency>()

        handlingChangesRatesUseCase.handlingChangesRates(list)

        verify(exactly = 1) { LastCurrenciesList.lastList = list}
    }

    @Test
    fun `handlingChangesRates() should be update LastCurrenciesList for the first time`() {
        mockkObject(LastCurrenciesList)
        every { LastCurrenciesList.lastList } returns null
        val handlingChangesRatesUseCase = HandlingChangesRatesUseCase()
        val list = listOf<Currency>()

        handlingChangesRatesUseCase.handlingChangesRates(list)

        verify(exactly = 1) { LastCurrenciesList.lastList = list}
    }

    @Test
    fun `handlingChangesRates() should be return the same list for the first time`() {
        mockkObject(LastCurrenciesList)
        every { LastCurrenciesList.lastList } returns null
        val handlingChangesRatesUseCase = HandlingChangesRatesUseCase()
        val list = listOf<Currency>()

        val newList = handlingChangesRatesUseCase.handlingChangesRates(list)

        verify(exactly = 1) { LastCurrenciesList.lastList = list}
        assertSame(list, newList)
    }

    @Test
    fun `handlingChangesRates() should be define the state WITHOUT_CHANGES of currency when oldRate equal newRate`() {
        mockkObject(LastCurrenciesList)
        every { LastCurrenciesList.lastList } returns listOf(Currency("USD", 0.0125, StateOfChangesRates.WITHOUT_CHANGES))
        val handlingChangesRatesUseCase = HandlingChangesRatesUseCase()
        val list = listOf(Currency("USD", 0.0125, StateOfChangesRates.WITHOUT_CHANGES))

        val newList = handlingChangesRatesUseCase.handlingChangesRates(list)

        assertEquals(StateOfChangesRates.WITHOUT_CHANGES, newList.first().stateOfChangesRates)
    }

    @Test
    fun `handlingChangesRates() should be define the state INCREASED of currency when oldRate less then newRate`() {
        mockkObject(LastCurrenciesList)
        every { LastCurrenciesList.lastList } returns listOf(Currency("USD", 0.0124, StateOfChangesRates.WITHOUT_CHANGES))
        val handlingChangesRatesUseCase = HandlingChangesRatesUseCase()
        val list = listOf(Currency("USD", 0.0125, StateOfChangesRates.WITHOUT_CHANGES))

        val newList = handlingChangesRatesUseCase.handlingChangesRates(list)

        assertEquals(StateOfChangesRates.INCREASED, newList.first().stateOfChangesRates)
    }

    @Test
    fun `handlingChangesRates() should be define the state WITHOUT_CHANGES of currency when oldRate greater then newRate`() {
        mockkObject(LastCurrenciesList)
        every { LastCurrenciesList.lastList } returns listOf(Currency("USD", 0.0126, StateOfChangesRates.WITHOUT_CHANGES))
        val handlingChangesRatesUseCase = HandlingChangesRatesUseCase()
        val list = listOf(Currency("USD", 0.0125, StateOfChangesRates.WITHOUT_CHANGES))

        val newList = handlingChangesRatesUseCase.handlingChangesRates(list)

        assertEquals(StateOfChangesRates.DECREASED, newList.first().stateOfChangesRates)
    }
}