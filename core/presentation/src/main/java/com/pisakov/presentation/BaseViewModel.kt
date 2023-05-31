package com.pisakov.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisakov.presentation.liveEvent.Event
import com.pisakov.presentation.liveEvent.LiveEvent
import com.pisakov.presentation.liveEvent.LiveValue
import com.pisakov.presentation.liveEvent.MutableLiveValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    protected fun <T> liveEvent(): LiveEvent<T> {
        return MutableLiveValue()
    }

    protected var <T> LiveValue<T>.value: T
        get() = this.requireValue()
        set(value) {
            (this as? MutableLiveValue)?.setValue(value)
        }

    protected fun <T> LiveEvent<T>.publish(event: T) {
        viewModelScope.launch(Dispatchers.Main) {
            this@publish.value = Event(event)
        }
    }
}