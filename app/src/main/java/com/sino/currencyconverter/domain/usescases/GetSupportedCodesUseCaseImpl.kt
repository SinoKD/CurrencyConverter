package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.data.repository.ConvertRepository
import com.sino.currencyconverter.utils.ErrorUtils
import retrofit2.HttpException
import javax.inject.Inject

interface GetSupportedCodesUseCase {
    suspend fun getSupportedCountryCodes(): Result<List<List<String>>>
}

class GetSupportedCodesUseCaseImpl @Inject constructor(
    private val repository: ConvertRepository
) : GetSupportedCodesUseCase {
    override
    suspend fun getSupportedCountryCodes(): Result<List<List<String>>> {
        return try {
            val response = repository.getCountryCodes()
            Result.Success(response.supportedCodes)
        } catch (httpException: HttpException) {
            Result.Error(
                ErrorUtils.parseError(httpException.response()?.errorBody()).errorType
                    ?: "Something Went Wrong!!"
            )
        } catch (e: Exception) {
            Result.Error(ErrorUtils.parseError(e).errorType ?: "Something Went Wrong!!")
        }
    }
}

