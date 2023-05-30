package com.pisakov.currencyconverter.presentation.currencyList

import androidx.lifecycle.viewModelScope
import com.pisakov.common.Logger
import com.pisakov.common.Resources
import com.pisakov.currencyconverter.R
import com.pisakov.currencyconverter.domain.currencyList.GetCurrenciesUseCase
import com.pisakov.currencyconverter.domain.entities.Currency
import com.pisakov.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val logger: Logger,
    private val resources: Resources
) : BaseViewModel() {

    private var _currenciesStateFlow = MutableSharedFlow<List<Currency>>(1)
    val currenciesStateFlow = _currenciesStateFlow.asSharedFlow()

    val errorLiveEvent = liveEvent<String>()

    init {
        getCurrenciesList()
        updateCurrencyRates()
    }

    private fun getCurrenciesList() {
        viewModelScope.launch {
            getCurrenciesUseCase.getCurrencies().collect {
                _currenciesStateFlow.emit(it)
            }
        }
    }

    fun updateCurrencyRates() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    getCurrenciesUseCase.updateCurrencies()
                } catch (e: UnknownHostException) {
                    logger.err(e, "unknown host")
                    errorLiveEvent.publish(resources.getString(R.string.data_loading_error))
                } catch (e: Exception) {
                    logger.err(e, "api response not success")
                    errorLiveEvent.publish(resources.getString(R.string.error))
                }
            }
        }
    }

    fun getUpdateTime(): String {
        return SimpleDateFormat(DATE_TIME_PATTERN, Locale.getDefault()).format(Date())
    }

    companion object {
        const val DATE_TIME_PATTERN = "dd.MM.yy HH:mm:ss"
    }
}