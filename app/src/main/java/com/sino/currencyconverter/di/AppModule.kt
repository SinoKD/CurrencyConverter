package com.sino.currencyconverter.di

import com.google.gson.Gson
import com.sino.currencyconverter.data.NetworkAPI
import com.sino.currencyconverter.data.repository.ConvertRepository
import com.sino.currencyconverter.domain.repository.ConvertRemoteRepository
import com.sino.currencyconverter.domain.usescases.GetConversionRatesUseCase
import com.sino.currencyconverter.domain.usescases.GetConversionRatesUseCaseImpl
import com.sino.currencyconverter.domain.usescases.GetSupportedCodesUseCase
import com.sino.currencyconverter.domain.usescases.GetSupportedCodesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideConvertRemoteRepository(api: NetworkAPI): ConvertRepository =
        ConvertRemoteRepository(api)

    @Singleton
    @Provides
    fun provideCountryCodesUseCase(convertRepository: ConvertRepository): GetSupportedCodesUseCase =
        GetSupportedCodesUseCaseImpl(convertRepository)

    @Singleton
    @Provides
    fun provideConversionRatesUseCase(convertRepository: ConvertRepository): GetConversionRatesUseCase =
        GetConversionRatesUseCaseImpl(convertRepository)
}