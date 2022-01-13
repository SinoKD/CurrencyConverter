package com.sino.currencyconverter.utils

import kotlin.math.round

object AppUtility {
    fun calculateConversion(
        amount: Double,
        rate: Double,
        isTargetAmtChanged: Boolean
    ): String {
        return if (isTargetAmtChanged) {
            "${round((amount / rate) * 100) / 100}"
        } else {
            "${round((amount * rate) * 100) / 100}"
        }
    }
}