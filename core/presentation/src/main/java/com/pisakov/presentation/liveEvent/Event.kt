package com.pisakov.presentation.liveEvent

import androidx.lifecycle.LifecycleOwner

class Event<T>(value: T) {

    private var _value: T? = value

    fun get(): T? = _value.also { _value = null }

}

typealias LiveEvent<T> = LiveValue<Event<T>>

fun <T> LiveEvent<T>.observeEvent(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    observe(lifecycleOwner) { event ->
        val value = event.get() ?: return@observe
        observer(value)
    }
}