package com.pisakov.currencyconverter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pisakov.currencyconverter.databinding.FragmentCurrenciesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {
    private val binding: FragmentCurrenciesBinding by viewBinding(FragmentCurrenciesBinding::bind)
    private val viewModel: CurrenciesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}