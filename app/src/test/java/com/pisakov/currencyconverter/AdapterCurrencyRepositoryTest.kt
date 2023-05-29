package com.pisakov.currencyconverter

import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.data.currency.repositories.CurrencyDataRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AdapterCurrencyRepositoryTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var currencyDataRepository: CurrencyDataRepositoryImpl

    @Test
    fun getCurrenciesMappingToCurrencyIsCorrect() = runTest {
        val targetFlow = flowOf(mapOf(Pair("USD", 0.0125)))
        every { currencyDataRepository.getCurrencyRates() } returns targetFlow
        val targetCurrency = Currency("USD", 0.0125)
        val adapterCurrencyRepository = AdapterCurrencyRepository(currencyDataRepository)

        val flowAfterMapping = adapterCurrencyRepository.getCurrencies()

        assertEquals(targetFlow.count(), flowAfterMapping.count())
        assertEquals(targetCurrency, flowAfterMapping.first().first())
    }

    @Test
    fun `getCurrencies() should be call getCurrencyRates() from data repository`() {
        every { currencyDataRepository.getCurrencyRates() } returns flowOf()
        val adapterCurrencyRepository = AdapterCurrencyRepository(currencyDataRepository)

        adapterCurrencyRepository.getCurrencies()

        coVerify(exactly = 1) {
            currencyDataRepository.getCurrencyRates()
        }
    }

    @Test
    fun `updateRates() should be call updateCurrenciesRates() from repository`() = runTest {
        coEvery { currencyDataRepository.updateCurrencyRates() } just runs
        val adapterCurrencyRepository = AdapterCurrencyRepository(currencyDataRepository)

        adapterCurrencyRepository.updateRates()

        coVerify(exactly = 1) {
            currencyDataRepository.updateCurrencyRates()
        }
    }
}