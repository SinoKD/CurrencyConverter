package com.sino.currencyconverter.domain.usescases

import com.sino.currencyconverter.data.models.ConversionRatesApiResponse
import com.sino.currencyconverter.data.models.SupportedCodesApiResponse
import com.sino.currencyconverter.data.repository.ConvertRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class FakeFailRepoWithHttpException : ConvertRepository {

    private val mediaTypeStr = "application/json"
    private val errorResponseStr = "{\"result\":\"error\",\"error-type\":\"inactive-account\"}"
    private val errorResponseBody = errorResponseStr.toResponseBody(mediaTypeStr.toMediaType())

    override suspend fun getCountryCodes(): SupportedCodesApiResponse {
        throw HttpException(Response.error<ResponseBody>(404,errorResponseBody))
    }

    override suspend fun getConversionRates(baseCurrency: String): ConversionRatesApiResponse {
        throw HttpException(Response.error<ResponseBody>(404,errorResponseBody))
    }

}

class FakeFailRepoWithCommonException : ConvertRepository {
    override suspend fun getCountryCodes(): SupportedCodesApiResponse {
        throw UnknownHostException("Unable to resolve host \"v6.exchangerate-api.com\": No address associated with hostname")
    }

    override suspend fun getConversionRates(baseCurrency: String): ConversionRatesApiResponse {
        throw throw UnknownHostException("Unable to resolve host \"v6.exchangerate-api.com\": No address associated with hostname")
    }
}