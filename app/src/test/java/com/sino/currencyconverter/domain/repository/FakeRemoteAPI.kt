package com.sino.currencyconverter.domain.repository

import com.sino.currencyconverter.data.NetworkAPI
import com.sino.currencyconverter.data.models.ConversionRatesApiResponse
import com.sino.currencyconverter.data.models.SupportedCodesApiResponse
import com.sino.currencyconverter.utils.ConversionRateTestUtil

class FakeRemoteAPI: NetworkAPI {
    override suspend fun getCountryCodes(): SupportedCodesApiResponse {
       return ConversionRateTestUtil.getFakeSupportedCodes()
    }

    override suspend fun getConversionRates(baseCurrency: String): ConversionRatesApiResponse {
        return ConversionRateTestUtil.getFakeConversionRatesApiResponseForUSD()
    }
}