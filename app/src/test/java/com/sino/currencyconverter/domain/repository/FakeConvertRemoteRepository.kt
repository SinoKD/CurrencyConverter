package com.sino.currencyconverter.domain.repository

import com.sino.currencyconverter.data.models.ConversionRatesApiResponse
import com.sino.currencyconverter.data.models.SupportedCodesApiResponse
import com.sino.currencyconverter.data.repository.ConvertRepository
import com.sino.currencyconverter.utils.ConversionRateTestUtil

class FakeConvertRemoteRepository : ConvertRepository {

    override suspend fun getCountryCodes(): SupportedCodesApiResponse {
        return ConversionRateTestUtil.getFakeSupportedCodes()
    }

    override suspend fun getConversionRates(baseCurrency: String): ConversionRatesApiResponse {
        return ConversionRateTestUtil.getFakeConversionRatesApiResponseForUSD()
    }
}