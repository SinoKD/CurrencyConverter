package com.sino.currencyconverter.data.models

import com.google.gson.annotations.SerializedName

data class ConversionRatesApiResponse(

    @SerializedName("result")
    val result: String,

    @SerializedName("base_code")
    val baseCode: String,

    @SerializedName("conversion_rates")
    val conversionRates: Map<String,Double>,

    @SerializedName("documentation")
    val documentation: String?=null,

    @SerializedName("terms_of_use")
    val termsOfUse: String?=null,

    @SerializedName("time_last_update_unix")
    val timeLastUpdate_unix: String?=null,

    @SerializedName("time_last_update_utc")
    val timeLastUpdateUtc: String?=null,

    @SerializedName("time_next_update_unix")
    val timeNextUpdateUnix: String?=null,

    @SerializedName("time_next_update_utc")
    val timeNextUpdateUtc: String?=null
)