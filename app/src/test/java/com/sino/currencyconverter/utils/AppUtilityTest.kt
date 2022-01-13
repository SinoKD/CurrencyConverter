package com.sino.currencyconverter.utils

import com.google.common.truth.Truth
import org.junit.Test

class AppUtilityTest {

    @Test
    fun calculateConversionForFromAmtChanged() {
        Truth.assertThat(AppUtility.calculateConversion(100.0,1.5,false)).isEqualTo("150.0")
    }

    @Test
    fun calculateConversionForToAmtChanged() {
        Truth.assertThat(AppUtility.calculateConversion(100.0,1.5,true)).isEqualTo("66.67")
    }
}