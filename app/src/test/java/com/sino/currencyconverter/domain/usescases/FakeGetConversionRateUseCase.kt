package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.utils.ConversionRateTestUtil

class FakeGetConversionRateUseCase : GetConversionRatesUseCase {
    override suspend fun getConversionRates(baseCurrencyCode: String): Result<Map<String,Double>> {
        return Result.Success(ConversionRateTestUtil.getFakeConversionRatesForUSD())
    }
}