package com.pisakov.currencyconverter.presentation.currencyList

import com.pisakov.common.Logger
import com.pisakov.common.Resources
import com.pisakov.currencyconverter.domain.currencyList.GetCurrenciesUseCase
import com.pisakov.currencyconverter.domain.currencyList.HandlingChangesRatesUseCase
import com.pisakov.currencyconverter.testUtils.TestViewModelScopeRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class CurrenciesViewModelTest {
    @get:Rule
    val rule = MockKRule(this)
    @get:Rule
    val testViewModelScopeRule = TestViewModelScopeRule()

    @RelaxedMockK
    lateinit var getCurrenciesUseCase: GetCurrenciesUseCase
    @MockK
    lateinit var handlingChangesRatesUseCase: HandlingChangesRatesUseCase
    @RelaxedMockK
    lateinit var logger: Logger
    @MockK
    lateinit var resources: Resources

    @Test
    fun `updateCurrencyRates() should be call updateCurrencies() from GetCurrenciesUseCase`() {
        val viewModel = CurrenciesViewModel(getCurrenciesUseCase, handlingChangesRatesUseCase, logger, resources)

        viewModel.updateCurrencyRates()

        coVerify { getCurrenciesUseCase.updateCurrencies() }
    }

    @Test
    fun `updateCurrencyRates() shouldn't throw UnknownHostException`() {
        val viewModel = CurrenciesViewModel(getCurrenciesUseCase, handlingChangesRatesUseCase, logger, resources)
        coEvery { getCurrenciesUseCase.updateCurrencies() } throws UnknownHostException()

        viewModel.updateCurrencyRates()
    }

    @Test
    fun `updateCurrencyRates() shouldn't throw Exception`() {
        val viewModel = CurrenciesViewModel(getCurrenciesUseCase, handlingChangesRatesUseCase, logger, resources)
        coEvery { getCurrenciesUseCase.updateCurrencies() } throws Exception()

        viewModel.updateCurrencyRates()
    }
}