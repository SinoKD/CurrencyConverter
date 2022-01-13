package com.sino.currencyconverter.domain.usescases

import com.google.common.truth.Truth
import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.domain.repository.FakeConvertRemoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Test

@ExperimentalCoroutinesApi
class GetConversionRatesUseCaseImplTest {

    private val apiErrorStr = "inactive-account"
    private lateinit var getConversionRatesUseCase: GetConversionRatesUseCaseImpl
    private lateinit var getConversionRatesUseCaseHttpError: GetConversionRatesUseCaseImpl
    private lateinit var getConversionRatesUseCaseHostError: GetConversionRatesUseCaseImpl

    @Before
    fun setUp() {
        getConversionRatesUseCase = GetConversionRatesUseCaseImpl(FakeConvertRemoteRepository())
        getConversionRatesUseCaseHttpError =
            GetConversionRatesUseCaseImpl(FakeFailRepoWithHttpException())
        getConversionRatesUseCaseHostError =
            GetConversionRatesUseCaseImpl(FakeFailRepoWithCommonException())
    }

    @Test
    fun getConversionRates() {
        runBlockingTest {
            when (val rates = getConversionRatesUseCase.getConversionRates("USD")) {
                is Result.Success -> Truth.assertThat(rates.data?.get("SGD")).isEqualTo(1.3547)
                else -> Truth.assert_().fail()
            }
        }
    }

    @Test
    fun getConversionRatesHttpException() {
        runBlockingTest {
            when (val rates = getConversionRatesUseCaseHttpError.getConversionRates("USD")) {
                is Result.Error -> Truth.assertThat(rates.message).contains(apiErrorStr)
                else -> Truth.assert_().fail()
            }
        }
    }

    @Test
    fun getConversionRatesException() {
        runBlockingTest {
            when (val rates = getConversionRatesUseCaseHostError.getConversionRates("USD")) {
                is Result.Error -> Truth.assertThat(rates.message).contains("Network error")
                else -> Truth.assert_().fail()
            }
        }
    }
}
