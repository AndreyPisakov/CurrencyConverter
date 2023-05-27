package com.pisakov.currencyconverter.presentation.currencyList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.ScreenMetrics
import com.pisakov.currencyconverter.databinding.ItemCurrencyBinding
import com.pisakov.currencyconverter.domain.entities.Currency

class CurrencyRecyclerAdapter(private val click: (currency: Currency) -> Unit) :
    ListAdapter<Currency, CurrencyRecyclerAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        println("!!!!!!!!!!!!!!1dfgdfgdfgdfgdfgdfgdfgdfgdfgdfg")
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        println("!!!!!!!!!!!!!!2dfgdfgdfgdfgdfgdfgdfgdfgdfgdfg")
        holder.bind(item)
        holder.binding.root.setOnClickListener { click(item) }
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCurrencyBinding.bind(itemView)

        fun bind(currency: Currency) {
            binding.apply {
                cardView.layoutParams.width = ScreenMetrics.screenWidth / 2 - ScreenMetrics.dpToPx
                tvCurrency.text = currency.currencyCode
                tvRate.text = currency.rate.toString()
            }
        }
    }

    class CurrencyDiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem.currencyCode == newItem.currencyCode

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem == newItem
    }
}