package com.sino.currencyconverter.domain.repository

import com.sino.currencyconverter.data.NetworkAPI
import com.sino.currencyconverter.data.models.ConversionRatesApiResponse
import com.sino.currencyconverter.data.models.SupportedCodesApiResponse
import com.sino.currencyconverter.data.repository.ConvertRepository
import javax.inject.Inject

class ConvertRemoteRepository @Inject constructor(private val api: NetworkAPI) : ConvertRepository {
    override suspend fun getCountryCodes(): SupportedCodesApiResponse {
        return api.getCountryCodes()
    }

    override suspend fun getConversionRates(baseCurrency: String): ConversionRatesApiResponse {
        return api.getConversionRates(baseCurrency)
    }
}