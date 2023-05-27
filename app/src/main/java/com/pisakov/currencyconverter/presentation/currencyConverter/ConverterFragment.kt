package com.pisakov.currencyconverter.presentation.currencyConverter

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.databinding.FragmentConverterBinding
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.presentation.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.fragment_converter) {
    private val binding: FragmentConverterBinding by viewBinding()
    private val viewModel: ConverterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currency: Currency = ConverterFragmentArgs.fromBundle(requireArguments()).currency

        val rateText = "${currency.rate} ${currency.currencyCode}"
        with(binding) {
            tvRate.text = rateText

            etCurrency.hint = currency.currencyCode

            etRubles.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(RUB_PATTERN.toPattern()))
            etRubles.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (viewModel.validateCurrencyAmount(s.toString()))
                        etCurrency.setText(viewModel.convertCurrency(s.toString(), currency.rate))
                    else {
                        etCurrency.setText("")
                        etRubles.error = getString(R.string.incorrect_input)
                    }
                }
            })
        }
    }

    companion object {
        const val RUB_PATTERN = "[0-9]{0,13}((\\.[0-9]{0,1})?)|(\\.)?"
    }
}

class DecimalDigitsInputFilter(private val mPattern: Pattern) : InputFilter {
    override fun filter(source: CharSequence, start: Int, end: Int,
        dest: Spanned, dstart: Int, dend: Int): CharSequence? {
            val matcher = mPattern.matcher(dest)
            return if (!matcher.matches()) "" else null
    }
}