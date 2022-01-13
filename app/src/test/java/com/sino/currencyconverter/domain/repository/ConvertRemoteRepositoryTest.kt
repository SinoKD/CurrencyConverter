package com.sino.currencyconverter.domain.repository

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
@ExperimentalCoroutinesApi
class ConvertRemoteRepositoryTest {

    private lateinit var convertRemoteRepository: ConvertRemoteRepository

    @Before
    fun setup(){
        convertRemoteRepository = ConvertRemoteRepository(FakeRemoteAPI())
    }


    @Test
    fun getCountryCodes() {
        runBlockingTest {
            val codes = convertRemoteRepository.getCountryCodes()
            Truth.assertThat(codes.supportedCodes).hasSize(7)
        }

    }

    @Test
    fun getConversionRates() {
        runBlockingTest {
            val rates = convertRemoteRepository.getConversionRates("USD")
            Truth.assertThat(rates.conversionRates["SGD"]).isEqualTo(1.3547)
        }
    }
}