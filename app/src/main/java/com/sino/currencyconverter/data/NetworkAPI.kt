package com.sino.currencyconverter.data

import com.sino.currencyconverter.data.models.ConversionRatesApiResponse
import com.sino.currencyconverter.data.models.SupportedCodesApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkAPI {

    @GET("codes")
    suspend fun getCountryCodes(): SupportedCodesApiResponse

    @GET("latest/{base_currency}")
    suspend fun getConversionRates(
        @Path(
            "base_currency"
        ) baseCurrency: String
    ): ConversionRatesApiResponse
}