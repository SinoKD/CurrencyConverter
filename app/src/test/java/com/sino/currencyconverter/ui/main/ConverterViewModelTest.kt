package com.sino.currencyconverter.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sino.currencyconverter.domain.usescases.FakeErrorCodeUseCase
import com.sino.currencyconverter.domain.usescases.FakeErrorConversionRateUseCase
import com.sino.currencyconverter.domain.usescases.FakeGetConversionRateUseCase
import com.sino.currencyconverter.domain.usescases.FakeGetSupportedCodesUseCase
import com.sino.currencyconverter.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ConverterViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ConverterViewModel

    private lateinit var viewModelErrorCase:ConverterViewModel

    private val apiErrorStr = "inactive-account"

    @Before
    fun setup() {
        viewModel = ConverterViewModel(
            FakeGetSupportedCodesUseCase(),
            FakeGetConversionRateUseCase()
        )

        viewModelErrorCase  = ConverterViewModel(
            FakeErrorCodeUseCase(),
            FakeErrorConversionRateUseCase()
        )
    }

    @Test
    fun getCountryCodes() {
        mainCoroutineRule.pauseDispatcher()
        runBlockingTest { viewModel.getCountryCodes() }
        viewModel.countryCodes.observeForTesting {
            assertThat(viewModel.countryCodes.getOrAwaitValue().isLoading).isTrue()
            mainCoroutineRule.resumeDispatcher()
            assertThat(viewModel.countryCodes.getOrAwaitValue().isLoading).isFalse()
            assertThat(viewModel.countryCodes.getOrAwaitValue().result).hasSize(7)
        }
    }

    @Test
    fun getCountryCodeSourceError(){
        mainCoroutineRule.pauseDispatcher()
        runBlockingTest { viewModelErrorCase.getCountryCodes() }
        viewModelErrorCase.countryCodes.observeForTesting {
            assertThat(viewModelErrorCase.countryCodes.getOrAwaitValue().isLoading).isTrue()
            mainCoroutineRule.resumeDispatcher()
            assertThat(viewModelErrorCase.countryCodes.getOrAwaitValue().isLoading).isFalse()
            assertThat(viewModelErrorCase.countryCodes.getOrAwaitValue().errorMessage).isEqualTo(apiErrorStr)
        }
    }

    @Test
    fun setBaseCountryCode() {
        viewModel.fromAmountChanged(250.0)
        viewModel.setTargetCountryCode("SGD")
        viewModel.setConversionRates(ConversionRateTestUtil.getFakeConversionRatesForUSD())

        viewModel.setBaseCountryCode("USD")
        viewModel.paramChangeLiveData.observeForTesting {
            mainCoroutineRule.resumeDispatcher()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isLoading).isFalse()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isToAmtResult).isTrue()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().result).contains("338.68")
        }
    }

    @Test
    fun setTargetCountryCode() {
        viewModel.setBaseCountryCode("USD")
        viewModel.fromAmountChanged(200.0)
        viewModel.setConversionRates(ConversionRateTestUtil.getFakeConversionRatesForUSD())

        viewModel.setTargetCountryCode("SGD")
        viewModel.paramChangeLiveData.observeForTesting {
            mainCoroutineRule.resumeDispatcher()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isLoading).isFalse()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().result).contains("270.94")
        }
    }

    @Test
    fun fromAmountChanged() {
        viewModel.setBaseCountryCode("USD")
        viewModel.setTargetCountryCode("SGD")
        viewModel.setConversionRates(ConversionRateTestUtil.getFakeConversionRatesForUSD())
        viewModel.fromAmountChanged(100.0)

        viewModel.paramChangeLiveData.observeForTesting {
            mainCoroutineRule.resumeDispatcher()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isLoading).isFalse()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isToAmtResult).isTrue()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().result).contains("135.47")
        }
    }

    @Test
    fun toAmountChanged() {
        viewModel.setBaseCountryCode("USD")
        viewModel.setTargetCountryCode("SGD")
        viewModel.setConversionRates(ConversionRateTestUtil.getFakeConversionRatesForUSD())
        viewModel.toAmountChanged(100.0)
        viewModel.paramChangeLiveData.observeForTesting {
            mainCoroutineRule.resumeDispatcher()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isLoading).isFalse()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isToAmtResult).isFalse()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().isFromAmtResult).isTrue()
            assertThat(viewModel.paramChangeLiveData.getOrAwaitValue().result).contains("73.82")
        }
    }
}