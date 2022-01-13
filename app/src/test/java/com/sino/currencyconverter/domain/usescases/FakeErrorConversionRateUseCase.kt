package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.Result

class FakeErrorConversionRateUseCase: GetConversionRatesUseCase {
    private val apiErrorStr = "inactive-account"
    override suspend fun getConversionRates(baseCurrencyCode: String): Result<Map<String,Double>> {
        return Result.Error(apiErrorStr)
    }
}