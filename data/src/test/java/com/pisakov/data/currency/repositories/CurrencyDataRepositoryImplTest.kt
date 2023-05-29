package com.pisakov.data.currency.repositories

import com.pisakov.common.Logger
import com.pisakov.data.currency.entities.CurrencyApiResponse
import com.pisakov.data.currency.sources.CurrencyApi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyDataRepositoryImplTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var currencyApi: CurrencyApi

    @MockK
    lateinit var logger: Logger

    @Test
    fun `getCurrencyRates() should be return private field flow`() {
        val currencyDataRepositoryImpl = CurrencyDataRepositoryImpl(currencyApi, logger)

        val flow = currencyDataRepositoryImpl.getCurrencyRates()

        assertEquals(currencyDataRepositoryImpl.currencyFlow, flow)
    }

    @Test
    fun `getCurrencyRates() should be return the same flow on different calls`() {
        val currencyDataRepositoryImpl = CurrencyDataRepositoryImpl(currencyApi, logger)

        val flow1 = currencyDataRepositoryImpl.getCurrencyRates()
        val flow2 = currencyDataRepositoryImpl.getCurrencyRates()

        assertEquals(flow1, flow2)
    }

    @Test
    fun `getCurrencyRates() should be return the same flow after call updateCurrencyRates()`() = runTest {
        val apiResponse: CurrencyApiResponse = mockk()
        every { apiResponse.conversionRates } returns mapOf(Pair("USD", 0.0125))
        coEvery { currencyApi.getCurrencyFromApi() } returns apiResponse
        val currencyDataRepositoryImpl = CurrencyDataRepositoryImpl(currencyApi, logger)

        val flow1 = currencyDataRepositoryImpl.getCurrencyRates()
        currencyDataRepositoryImpl.updateCurrencyRates()
        val flow2 = currencyDataRepositoryImpl.getCurrencyRates()

        assertEquals(flow1, flow2)
    }

    @Test
    fun `updateCurrencyRates() should be put returned map from api to currencyFlow`() = runTest {
        val apiResponse: CurrencyApiResponse = mockk()
        val targetMap = mapOf(Pair("USD", 0.0125), Pair("RUB", 1.0))
        every { apiResponse.conversionRates } returns targetMap
        coEvery { currencyApi.getCurrencyFromApi() } returns apiResponse
        val currencyDataRepositoryImpl = CurrencyDataRepositoryImpl(currencyApi, logger)

        currencyDataRepositoryImpl.updateCurrencyRates()

        assertEquals(targetMap, currencyDataRepositoryImpl.currencyFlow.first())
    }

    @Test
    fun `updateCurrencyRates() should be catch UnknownHostException and call err() from logger`() = runTest {
        val logger: Logger = mockk(relaxed = true)
        val currencyDataRepositoryImpl = CurrencyDataRepositoryImpl(currencyApi, logger)
        coEvery { currencyDataRepositoryImpl.updateCurrencyRates() } throws UnknownHostException()

        try {
            currencyDataRepositoryImpl.updateCurrencyRates()
        } catch (_: UnknownHostException) {}

        verify(exactly = 1) { logger.err(any(), any()) }
    }
}