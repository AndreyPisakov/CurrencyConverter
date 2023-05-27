package com.pisakov.currencyconverter.presentation.currencyList

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.databinding.FragmentCurrenciesBinding
import com.pisakov.currencyconverter.presentation.ScreenMetrics
import com.pisakov.presentation.observeStateOn
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {
    private val binding: FragmentCurrenciesBinding by viewBinding()
    private val viewModel: CurrenciesViewModel by viewModels()

    private lateinit var currencyAdapter: CurrencyRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler(view)
        searching()

        viewModel.currenciesStateFlow.filter { it != null }.observeStateOn(viewLifecycleOwner) {
            currencyAdapter.submitList(it)
        }
    }

    private fun initRecycler(view: View) {
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
            layoutManager = GridLayoutManager(requireContext(), ScreenMetrics.SPAN_COUNT)
        }
    }

    private fun searching() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.currenciesStateFlow.filter { it != null }
                    .map { it!!.filter { currency -> currency.currencyCode.startsWith(newText, true) } }
                    .observeStateOn(viewLifecycleOwner) {
                        currencyAdapter.submitList(it)
                    }
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean { return false }
        })
    }

    override fun onResume() {
        super.onResume()
        ScreenMetrics.screenWidth = ScreenMetrics.getScreenWidthPx()
        ScreenMetrics.dpToPx = ScreenMetrics.getPaddingPx()
    }
}