package com.pisakov.data.currency.sources

import com.pisakov.data.currency.Constants
import com.pisakov.data.currency.entities.CurrencyApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("{api_key}/latest/{currency}")
    suspend fun getCurrencyFromApi(
        @Path("currency") baseCurrency: String = Constants.BASE_CURRENCY,
        @Path("api_key") apiKey: String = ApiKey.API_KEY,
    ): CurrencyApiResponse
}