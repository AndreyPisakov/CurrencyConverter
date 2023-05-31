package com.pisakov.currencyconverter.presentation.currencyConverter

import com.pisakov.currencyconverter.domain.currencyConverter.ConvertCurrencyUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ConvertViewModelTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var convertCurrencyUseCase: ConvertCurrencyUseCase

    @Test
    fun `validateCurrencyAmount() should be return false for empty string`() {
        val viewModel = ConverterViewModel(convertCurrencyUseCase)

        val isValidated = viewModel.validateCurrencyAmount("")

        assertEquals(false, isValidated)
    }

    @Test
    fun `validateCurrencyAmount() should be return false for string starting with a dot`() {
        val viewModel = ConverterViewModel(convertCurrencyUseCase)

        val isValidated = viewModel.validateCurrencyAmount("")

        assertEquals(false, isValidated)
    }

    @Test
    fun `validateCurrencyAmount() should be return true for correct string`() {
        val viewModel = ConverterViewModel(convertCurrencyUseCase)

        val isValidated = viewModel.validateCurrencyAmount("54")

        assertEquals(true, isValidated)
    }

    @Test
    fun `convertCurrency() should be return answer from convertCurrencyUseCase`() {
        val viewModel = ConverterViewModel(convertCurrencyUseCase)
        every { convertCurrencyUseCase.convertCurrency(any(), any()) } returns "0.675"

        val convertedCurrencyAmount = viewModel.convertCurrency("54", 0.0125)

        verify(exactly = 1) { convertCurrencyUseCase.convertCurrency(any(), any()) }
        assertEquals("0.675", convertedCurrencyAmount)
    }
}