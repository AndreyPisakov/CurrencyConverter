package com.pisakov.currencyconverter.presentation.currencyList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.ScreenMetrics
import com.pisakov.currencyconverter.databinding.FragmentCurrenciesBinding
import com.pisakov.presentation.observeStateOn
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {
    private val binding: FragmentCurrenciesBinding by viewBinding()
    private val viewModel: CurrenciesViewModel by viewModels()

    private lateinit var currencyAdapter: CurrencyRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            currencyAdapter = CurrencyRecyclerAdapter { currency ->
                view.findNavController()
                    .navigate(
                        CurrenciesFragmentDirections.actionCurrenciesFragmentToConverterFragment(
                            currency
                        )
                    )
            }
            adapter = currencyAdapter
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        }

        viewModel.getCurrenciesList().observeStateOn(viewLifecycleOwner) {
            currencyAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        ScreenMetrics.screenWidth = ScreenMetrics.getScreenWidthPx()
        ScreenMetrics.dpToPx = ScreenMetrics.getPaddingPx()
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}