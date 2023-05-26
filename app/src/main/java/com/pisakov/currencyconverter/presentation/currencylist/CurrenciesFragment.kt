package com.pisakov.currencyconverter.presentation.currencylist

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.databinding.FragmentCurrenciesBinding
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {
    private val binding: FragmentCurrenciesBinding by viewBinding()
    private val viewModel: CurrenciesViewModel by viewModels()
}