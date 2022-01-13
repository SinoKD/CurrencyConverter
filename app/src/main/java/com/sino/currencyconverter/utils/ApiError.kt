package com.sino.currencyconverter.utils


import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("error-type")
    val errorType: String? = null,
    @SerializedName("result")
    val result: String? = null
)