package com.sino.currencyconverter.data.models

import com.google.gson.annotations.SerializedName

data class SupportedCodesApiResponse(
    @SerializedName("documentation")
    val documentation: String?=null,
    @SerializedName("result")
    val result: String,
    @SerializedName("supported_codes")
    val supportedCodes: List<List<String>>,
    @SerializedName("terms_of_use")
    val termsOfUse: String?=null
)