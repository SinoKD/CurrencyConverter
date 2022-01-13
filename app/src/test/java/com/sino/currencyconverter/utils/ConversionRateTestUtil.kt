package com.sino.currencyconverter.utils

import com.sino.currencyconverter.data.models.ConversionRatesApiResponse
import com.sino.currencyconverter.data.models.SupportedCodesApiResponse

object ConversionRateTestUtil {
    fun getFakeSupportedCodes(): SupportedCodesApiResponse {
        val countryCodes = ArrayList<List<String>>()
        countryCodes.add(listOf("USD", "United States Dollar"))
        countryCodes.add(listOf("EUR", "Euro"))
        countryCodes.add(listOf("HKD", "Hong Kong Dollar"))
        countryCodes.add(listOf("SGD", "Singapore Dollar"))
        countryCodes.add(listOf("AUD", "Australian Dollar"))
        countryCodes.add(listOf("CAD", "Canadian Dollar"))
        countryCodes.add(listOf("INR", "Indian Rupee"))
        return SupportedCodesApiResponse(
            result = "success",
            supportedCodes = countryCodes
        )
    }

    fun getFakeConversionRatesForUSD(): Map<String,Double> {

        val ratesMap = HashMap<String,Double>()
        ratesMap["USD"] = 1.0
        ratesMap["AED"] = 3.6725
        ratesMap["AFN"] = 105.1273
        ratesMap["ALL"] = 107.5576
        ratesMap["AMD"] = 482.8685
        ratesMap["ANG"] = 1.7900
        ratesMap["AOA"] = 553.4894
        ratesMap["ARS"] = 103.3671
        ratesMap["AUD"] = 1.3942
        ratesMap["AWG"] = 1.7900
        ratesMap["AZN"] = 1.7021
        ratesMap["BAM"] = 1.7238
        ratesMap["BBD"] = 2.0000
        ratesMap["BDT"] = 86.0535
        ratesMap["BGN"] = 1.7241
        ratesMap["BHD"] = 0.3760
        ratesMap["BIF"] = 1993.4461
        ratesMap["BMD"] = 1.0000
        ratesMap["BND"] = 1.3547
        ratesMap["BOB"] = 6.9022
        ratesMap["BRL"] = 5.6911
        ratesMap["BSD"] = 1.0000
        ratesMap["BTN"] = 74.3252
        ratesMap["BWP"] = 11.7377
        ratesMap["BYN"] = 2.5923
        ratesMap["BZD"] = 2.0000
        ratesMap["CAD"] = 1.2673
        ratesMap["CDF"] = 1991.0664
        ratesMap["CHF"] = 0.9195
        ratesMap["CLP"] = 838.8012
        ratesMap["CNY"] = 6.3904
        ratesMap["COP"] = 4037.5862
        ratesMap["CRC"] = 643.2052
        ratesMap["CUP"] = 24.0000
        ratesMap["CVE"] = 97.1835
        ratesMap["CZK"] = 21.5859
        ratesMap["DJF"] = 177.7210
        ratesMap["DKK"] = 6.5753
        ratesMap["DOP"] = 57.4191
        ratesMap["DZD"] = 139.8255
        ratesMap["EGP"] = 15.7319
        ratesMap["ERN"] = 15.0000
        ratesMap["ETB"] = 49.6547
        ratesMap["EUR"] = 0.8814
        ratesMap["FJD"] = 2.1348
        ratesMap["FKP"] = 0.7371
        ratesMap["FOK"] = 6.5753
        ratesMap["GBP"] = 0.7371
        ratesMap["GEL"] = 3.0920
        ratesMap["GGP"] = 0.7371
        ratesMap["GHS"] = 6.4335
        ratesMap["GIP"] = 0.7371
        ratesMap["GMD"] = 53.0266
        ratesMap["GNF"] = 9237.2790
        ratesMap["GTQ"] = 7.7300
        ratesMap["GYD"] = 209.4816
        ratesMap["HKD"] = 7.7987
        ratesMap["HNL"] = 24.5586
        ratesMap["HRK"] = 6.6406
        ratesMap["HTG"] = 101.3608
        ratesMap["HUF"] = 317.6554
        ratesMap["IDR"] = 14335.3986
        ratesMap["ILS"] = 3.1289
        ratesMap["IMP"] = 0.7371
        ratesMap["INR"] = 74.3257
        ratesMap["IQD"] = 1461.1514
        ratesMap["IRR"] = 42016.9978
        ratesMap["ISK"] = 129.2489
        ratesMap["JEP"] = 0.7371
        ratesMap["JMD"] = 154.3972
        ratesMap["JOD"] = 0.7090
        ratesMap["JPY"] = 115.7123
        ratesMap["KES"] = 113.4489
        ratesMap["KGS"] = 84.7644
        ratesMap["KHR"] = 4078.5566
        ratesMap["KID"] = 1.3942
        ratesMap["KMF"] = 433.6024
        ratesMap["KRW"] = 1200.4089
        ratesMap["KWD"] = 0.2996
        ratesMap["KYD"] = 0.8333
        ratesMap["KZT"] = 436.2989
        ratesMap["LAK"] = 11260.6041
        ratesMap["LBP"] = 1507.5000
        ratesMap["LKR"] = 202.5376
        ratesMap["LRD"] = 146.5277
        ratesMap["LSL"] = 15.5804
        ratesMap["LYD"] = 4.6081
        ratesMap["MAD"] = 9.2578
        ratesMap["MDL"] = 17.9092
        ratesMap["MGA"] = 3233.0603
        ratesMap["MKD"] = 54.5348
        ratesMap["MMK"] = 1780.3006
        ratesMap["MNT"] = 2863.3604
        ratesMap["MOP"] = 8.0326
        ratesMap["MRU"] = 36.3516
        ratesMap["MUR"] = 43.8625
        ratesMap["MVR"] = 15.4182
        ratesMap["MWK"] = 819.8077
        ratesMap["MXN"] = 20.4249
        ratesMap["MYR"] = 4.2094
        ratesMap["MZN"] = 64.0396
        ratesMap["NAD"] = 15.5804
        ratesMap["NGN"] = 413.1913
        ratesMap["NIO"] = 35.4976
        ratesMap["NOK"] = 8.8434
        ratesMap["NPR"] = 118.9203
        ratesMap["NZD"] = 1.4770
        ratesMap["OMR"] = 0.3845
        ratesMap["PAB"] = 1.0000
        ratesMap["PEN"] = 3.9697
        ratesMap["PGK"] = 3.5163
        ratesMap["PHP"] = 51.4953
        ratesMap["PKR"] = 176.9714
        ratesMap["PLN"] = 4.0107
        ratesMap["PYG"] = 6935.6378
        ratesMap["QAR"] = 3.6400
        ratesMap["RON"] = 4.3489
        ratesMap["RSD"] = 104.0191
        ratesMap["RUB"] = 75.5605
        ratesMap["RWF"] = 1040.8554
        ratesMap["SAR"] = 3.7500
        ratesMap["SBD"] = 7.9712
        ratesMap["SCR"] = 13.9189
        ratesMap["SDG"] = 438.1247
        ratesMap["SEK"] = 9.0638
        ratesMap["SGD"] = 1.3547
        ratesMap["SHP"] = 0.7371
        ratesMap["SLL"] = 11269.9950
        ratesMap["SOS"] = 579.2310
        ratesMap["SRD"] = 21.3056
        ratesMap["SSP"] = 430.8104
        ratesMap["STN"] = 21.5934
        ratesMap["SYP"] = 2519.5946
        ratesMap["SZL"] = 15.5804
        ratesMap["THB"] = 33.7827
        ratesMap["TJS"] = 11.2889
        ratesMap["TMT"] = 3.4998
        ratesMap["TND"] = 2.8720
        ratesMap["TOP"] = 2.2709
        ratesMap["TRY"] = 13.8214
        ratesMap["TTD"] = 6.7965
        ratesMap["TVD"] = 1.3942
        ratesMap["TWD"] = 27.6526
        ratesMap["TZS"] = 2308.2860
        ratesMap["UAH"] = 27.5492
        ratesMap["UGX"] = 3540.3417
        ratesMap["UYU"] = 44.8271
        ratesMap["UZS"] = 10754.2076
        ratesMap["VES"] = 4.6122
        ratesMap["VND"] = 22689.0182
        ratesMap["VUV"] = 113.0495
        ratesMap["WST"] = 2.6061
        ratesMap["XAF"] = 578.1366
        ratesMap["XCD"] = 2.7000
        ratesMap["XDR"] = 0.7151
        ratesMap["XOF"] = 578.1366
        ratesMap["XPF"] = 105.1749
        ratesMap["YER"] = 250.5016
        ratesMap["ZAR"] = 15.5810
        ratesMap["ZMW"] = 16.8839
        ratesMap["ZWL"] = 108.6660

        return ratesMap
    }

    fun getFakeConversionRatesApiResponseForUSD(): ConversionRatesApiResponse {
        return ConversionRatesApiResponse(
            result = "success",
            baseCode = "USD",
            conversionRates = getFakeConversionRatesForUSD()
        )
    }
}