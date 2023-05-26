package com.pisakov.currencyconverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisakov.currencyconverter.domain.entities.Currency

object ConvertDataClass {
    private val gson = Gson()

    fun <T> T.serializeToMap(): List<Currency> {
        return convert()
    }

    private inline fun <I, reified O> I.convert(): O {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }
}