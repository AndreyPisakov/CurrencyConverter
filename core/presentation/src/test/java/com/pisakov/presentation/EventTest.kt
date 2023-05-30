package com.pisakov.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pisakov.presentation.liveEvent.Event
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class EventTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `get() should be return value only once`() {
        val event = Event("string")

        val value1 = event.get()
        val value2 = event.get()

        assertEquals("string", value1)
        assertNull(value2)
    }
}