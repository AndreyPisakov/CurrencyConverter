package com.pisakov.currencyconverter.domain.currencyList

import com.pisakov.currencyconverter.AdapterCurrencyRepository
import com.pisakov.currencyconverter.domain.entities.Currency
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrenciesUseCaseTest {

    @get:Rule
    val rule = MockKRule(this)

    @RelaxedMockK
    lateinit var adapterCurrencyRepository: AdapterCurrencyRepository

    @Test
    fun `getCurrencies() should be return flow which return from getCurrencies() from repository(adapter)`() = runTest{
        val targetFlow = flowOf(
            listOf(
                Currency("USD", 0.0125)
            )
        )
        every { adapterCurrencyRepository.getCurrencies() } returns targetFlow
        val getCurrenciesUseCase = GetCurrenciesUseCase(adapterCurrencyRepository)

        val flow = getCurrenciesUseCase.getCurrencies()

        assertEquals(targetFlow, flow)
        assertEquals(targetFlow.count(), flow.count())
        assertEquals(targetFlow.first().first(), flow.first().first())
    }

    @Test
    fun `getCurrencies() should be call getCurrencies() from repository(adapter)`() {
        val getCurrenciesUseCase = GetCurrenciesUseCase(adapterCurrencyRepository)

        getCurrenciesUseCase.getCurrencies()

        verify(exactly = 1) {
            adapterCurrencyRepository.getCurrencies()
        }
    }

    @Test
    fun `updateCurrencies() should be call updateRates() from repository(adapter)`() = runTest {
        val getCurrenciesUseCase = GetCurrenciesUseCase(adapterCurrencyRepository)

        getCurrenciesUseCase.updateCurrencies()

        coVerify(exactly = 1) {
            adapterCurrencyRepository.updateRates()
        }
    }
}