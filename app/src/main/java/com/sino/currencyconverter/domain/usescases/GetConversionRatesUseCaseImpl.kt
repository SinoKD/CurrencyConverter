package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.data.repository.ConvertRepository
import com.sino.currencyconverter.utils.ErrorUtils
import retrofit2.HttpException
import javax.inject.Inject


interface GetConversionRatesUseCase {
    suspend fun getConversionRates(baseCurrencyCode: String): Result<Map<String, Double>>
}

class GetConversionRatesUseCaseImpl @Inject constructor(
    private val repository: ConvertRepository
) : GetConversionRatesUseCase {
    override
    suspend fun getConversionRates(baseCurrencyCode: String): Result<Map<String, Double>> {
        return try {
            val response = repository.getConversionRates(baseCurrencyCode)
            Result.Success(response.conversionRates)
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