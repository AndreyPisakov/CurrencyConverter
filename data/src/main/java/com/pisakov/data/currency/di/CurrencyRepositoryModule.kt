package com.pisakov.data.currency.di

import com.pisakov.data.currency.repositotyes.CurrencyDataRepository
import com.pisakov.data.currency.repositotyes.CurrencyDataRepositoryImpl
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