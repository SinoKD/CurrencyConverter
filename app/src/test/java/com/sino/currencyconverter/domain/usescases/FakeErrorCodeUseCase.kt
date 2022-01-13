package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.Result

class FakeErrorCodeUseCase : GetSupportedCodesUseCase {
    private val apiErrorStr = "inactive-account"
    override suspend fun getSupportedCountryCodes(): Result<List<List<String>>> {
        return Result.Error(apiErrorStr)
    }
}