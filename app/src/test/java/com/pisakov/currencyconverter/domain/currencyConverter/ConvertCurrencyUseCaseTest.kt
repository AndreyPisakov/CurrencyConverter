package com.pisakov.currencyconverter.domain.currencyConverter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ConvertCurrencyUseCaseTest {
    @Test
    fun convertCurrencyWithCorrectValuesIsCorrect() {
        val convertCurrencyUseCase = ConvertCurrencyUseCase()

        val convertedCurrencyAmount = convertCurrencyUseCase.convertCurrency(1001.0, 0.5)

        assertEquals("500.5", convertedCurrencyAmount)
    }

    @Test
    fun `convertCurrency() value should be rounded to two decimal places`() {
        val convertCurrencyUseCase = ConvertCurrencyUseCase()

        val convertedCurrencyAmount = convertCurrencyUseCase.convertCurrency(1111.5, 0.11125)

        assertEquals("123.65", convertedCurrencyAmount)
        assertNotEquals("123.654375", convertedCurrencyAmount)
    }
}