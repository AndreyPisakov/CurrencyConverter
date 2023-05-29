package com.pisakov.data.currency.di

import com.pisakov.data.currency.repositories.CurrencyDataRepository
import com.pisakov.data.currency.repositories.CurrencyDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyRepositoryModule {
    @Binds
    @Singleton
    fun bindCurrencyRepository(
        currencyDataRepository: CurrencyDataRepositoryImpl
    ): CurrencyDataRepository
}