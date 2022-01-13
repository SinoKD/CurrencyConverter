package com.sino.currencyconverter.data.repository

import com.sino.currencyconverter.data.models.SupportedCodesApiResponse
import com.sino.currencyconverter.data.models.ConversionRatesApiResponse

interface ConvertRepository {
    suspend fun getCountryCodes(): SupportedCodesApiResponse

    suspend fun getConversionRates(baseCurrency: String): ConversionRatesApiResponse
}