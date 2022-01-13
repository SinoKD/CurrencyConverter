package com.sino.currencyconverter.utils

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.net.ConnectException
import java.net.UnknownHostException

object ErrorUtils {
    const val networkError = "Network error! Please check your network connection."
    const val generalError = "Oops! Something Went Wrong."
    fun parseError(errorResponse: ResponseBody?): ApiError {
        return try {
            if (errorResponse != null) {
                val errorString = errorResponse.string()
                Gson().fromJson(errorString, ApiError::class.java)
            } else {
                ApiError(errorType = generalError)
            }
        } catch (e: Exception) {
            ApiError(errorType = generalError)
        }
    }

    fun parseError(throwable: Throwable): ApiError {
        return if (throwable is ConnectException || throwable is UnknownHostException) {
            ApiError(errorType = networkError)
        } else {
            ApiError(errorType = throwable.message ?: generalError)
        }
    }
}