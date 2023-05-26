package com.pisakov.currencyconverter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pisakov.currencyconverter.databinding.FragmentCurrenciesBinding
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {
    private val binding: FragmentCurrenciesBinding by viewBinding()
    private val viewModel: CurrenciesViewModel by viewModels()
}