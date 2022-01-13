package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.utils.ConversionRateTestUtil

class FakeGetSupportedCodesUseCase : GetSupportedCodesUseCase{
    override suspend fun getSupportedCountryCodes(): Result<List<List<String>>> {
        return Result.Success(ConversionRateTestUtil.getFakeSupportedCodes().supportedCodes)
    }
}