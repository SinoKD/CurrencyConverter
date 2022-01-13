package com.sino.currencyconverter.domain.usescases

import com.google.common.truth.Truth
import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.domain.repository.FakeConvertRemoteRepository
import com.sino.currencyconverter.utils.ErrorUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetSupportedCodesUseCaseImplTest {
    private val apiErrorStr = "inactive-account"
    private lateinit var getSupportedCodesUseCaseImpl: GetSupportedCodesUseCaseImpl
    private lateinit var getSupportedCodesUseCaseImplError: GetSupportedCodesUseCaseImpl
    private lateinit var getConversionRatesUseCaseHostError: GetSupportedCodesUseCaseImpl

    @Before
    fun setUp() {
        getSupportedCodesUseCaseImpl = GetSupportedCodesUseCaseImpl(FakeConvertRemoteRepository())
        getSupportedCodesUseCaseImplError = GetSupportedCodesUseCaseImpl(FakeFailRepoWithHttpException())
        getConversionRatesUseCaseHostError = GetSupportedCodesUseCaseImpl(FakeFailRepoWithCommonException())
    }

    @Test
    fun getSupportedCountryCodes() {

        runBlockingTest {
            when(val codes = getSupportedCodesUseCaseImpl.getSupportedCountryCodes()){
                is Result.Error -> Truth.assert_().fail()
                is Result.Success -> Truth.assertThat(codes.data).hasSize(7)
            }
        }
    }

    @Test
    fun getConversionRatesHttpException() {
        runBlockingTest {
            when(val rates = getSupportedCodesUseCaseImplError.getSupportedCountryCodes()){
                is Result.Error -> Truth.assertThat(rates.message).isEqualTo(apiErrorStr)
                else -> Truth.assert_().fail()
            }
        }
    }

    @Test
    fun getConversionRatesException() {
        runBlockingTest {
            when(val rates = getConversionRatesUseCaseHostError.getSupportedCountryCodes()){
                is Result.Error -> Truth.assertThat(rates.message).contains(ErrorUtils.networkError)
                else -> Truth.assert_().fail()
            }
        }
    }
}