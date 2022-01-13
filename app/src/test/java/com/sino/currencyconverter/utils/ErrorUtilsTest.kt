package com.sino.currencyconverter.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class ErrorUtilsTest {

    private val apiErrorStr = "inactive-account"
    private val errorResponseStr = "{\"result\":\"error\",\"error-type\":\"inactive-account\"}"
    private val errorResponseInvalidJson = "{\"result\":\"error\",\"error-type\":\"inactive-account\""
    private val mediaTypeStr = "application/json"
    private val errorResponseBody = errorResponseStr.toResponseBody(mediaTypeStr.toMediaType())
    private val errorBodyInvalidJson = errorResponseInvalidJson.toResponseBody(mediaTypeStr.toMediaType())
    private val unknownHostException = UnknownHostException("Unable to resolve host \"v6.exchangerate-api.com\": No address associated with hostname")
    private val exception = Exception()

    @Test
    fun parseErrorWithApiError() {
        val error = ErrorUtils.parseError(errorResponseBody)
        Assert.assertEquals(error.errorType,apiErrorStr)
    }

    @Test
    fun parseErrorWithInvalidJson(){
        val error = ErrorUtils.parseError(null)
        Assert.assertEquals(error.errorType,ErrorUtils.generalError)
    }

    @Test
    fun parseErrorWithJsonParseException(){
        val error = ErrorUtils.parseError(errorBodyInvalidJson)
        Assert.assertEquals(error.errorType,ErrorUtils.generalError)
    }

    @Test
    fun parseErrorWithUnKnownHostException() {
        val error = ErrorUtils.parseError(unknownHostException)
        Assert.assertEquals(error.errorType,ErrorUtils.networkError)
    }

    @Test
    fun parseErrorWithException(){
        val error = ErrorUtils.parseError(exception)
        Assert.assertEquals(error.errorType,ErrorUtils.generalError)
    }
}