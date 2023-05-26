package com.pisakov.currencyconverter.presentation.currencyconverter

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.databinding.FragmentConverterBinding
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.fragment_converter) {
    private val binding: FragmentConverterBinding by viewBinding()
    private val viewModel: ConverterViewModel by viewModels()
}