package com.pisakov.currencyconverter.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.databinding.ChangesViewBinding
import com.pisakov.currencyconverter.domain.entities.StateOfChangesRates

class ChangesView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet) {

    private val binding = ChangesViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        setState(StateOfChangesRates.WITHOUT_CHANGES)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setState(state: StateOfChangesRates) {
        when (state) {
            StateOfChangesRates.INCREASED -> {
                binding.changesImageView.background = resources.getDrawable(R.drawable.increased_treangle, context.theme)
            }
            StateOfChangesRates.DECREASED -> {
                binding.changesImageView.background = resources.getDrawable(R.drawable.decreased_treangle, context.theme)
            }
            else -> {
                binding.changesImageView.background = resources.getDrawable(R.drawable.without_changes_line, context.theme)
            }
        }
    }
}