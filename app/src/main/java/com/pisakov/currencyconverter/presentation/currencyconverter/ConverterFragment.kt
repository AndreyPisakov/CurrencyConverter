package com.pisakov.currencyconverter.presentation.currencyconverter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.databinding.FragmentConverterBinding
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.fragment_converter) {
    private val binding: FragmentConverterBinding by viewBinding()
    private val viewModel: ConverterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currency: Currency = ConverterFragmentArgs.fromBundle(requireArguments()).currency

        val rateText = "${currency.rate} ${currency.currencyCode}"
        binding.tvRate.text = rateText
    }
}