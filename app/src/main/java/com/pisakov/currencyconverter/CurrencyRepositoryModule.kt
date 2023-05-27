package com.pisakov.currencyconverter

import com.pisakov.currencyconverter.domain.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyRepositoryModule {
    @Binds
    fun bindCurrencyRepository(adapterCurrencyRepository: AdapterCurrencyRepository): CurrencyRepository
}