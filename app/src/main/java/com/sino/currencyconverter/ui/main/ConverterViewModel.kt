package com.sino.currencyconverter.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sino.currencyconverter.data.Result
import com.sino.currencyconverter.domain.usescases.GetConversionRatesUseCase
import com.sino.currencyconverter.domain.usescases.GetSupportedCodesUseCase
import com.sino.currencyconverter.utils.AppUtility
import com.sino.currencyconverter.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ConverterUIState(
    val isLoading: Boolean = false,
    val isToAmtResult: Boolean = false,
    val isFromAmtResult: Boolean = false,
    val result: String? = null,
    val errorMessage: String? = null
)

data class CountryCodesUIState(
    val isLoading: Boolean = false,
    val result: List<List<String>>? = null,
    val errorMessage: String? = null
)

data class RatesUIState(
    val isLoading: Boolean = false,
    val status:String?="Failed",
    val errorMessage: String? = null
)

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val getSupportedCodesUseCase: GetSupportedCodesUseCase,
    private val getConversionRatesUseCase: GetConversionRatesUseCase
) : ViewModel() {

    private val _countryCodes = MutableLiveData<CountryCodesUIState>()
    val countryCodes = _countryCodes

    private var _baseCountyCode: String? = null
    private var _targetCountyCode : String? = null

    private val _fromAmount = MutableLiveData<Double>()
    private val _toAmount = MutableLiveData<Double>()
    private var _conversionRates: Map<String,Double>? = null
    private val _rateRefreshState = MutableLiveData<RatesUIState>()
    val rateRefreshState = _rateRefreshState
    private val _paramChangeLiveData = MediatorLiveData<ConverterUIState>()
    val paramChangeLiveData = _paramChangeLiveData


    init {
        _paramChangeLiveData.addSource(_fromAmount) {
            calculateConversionAmount()
        }
        _paramChangeLiveData.addSource(_toAmount) {
            calculateConversionAmount(isTargetAmtChanged = true)
        }
    }

    fun setBaseCountryCode(code: String?) {
        code?.let {
            _baseCountyCode = code
            refreshBaseCurrencyRates(it)
        }
    }

    fun setTargetCountryCode(code: String?) {
       code?.let {
           _targetCountyCode = it
       }
    }

    fun fromAmountChanged(amount: Double?) {
        _fromAmount.value = amount ?: 0.0
    }

    fun toAmountChanged(amount: Double?) {
        _toAmount.value = amount ?: 0.0
    }

    fun setConversionRates(conversionRates: Map<String,Double>) {
        _conversionRates = conversionRates
    }

    fun getCountryCodes() {
        _countryCodes.value = CountryCodesUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = getSupportedCodesUseCase.getSupportedCountryCodes()) {
                is Result.Error -> {
                    _countryCodes.value =
                        CountryCodesUIState(isLoading = false, errorMessage = result.message)
                }
                is Result.Success -> {
                    _countryCodes.value =
                        CountryCodesUIState(isLoading = false, result = result.data)
                }
            }
        }
    }

    private fun refreshBaseCurrencyRates(code: String?) {
        if (code != null) {
            _rateRefreshState.value = RatesUIState(isLoading = true)
            viewModelScope.launch {
                when (val result = getConversionRatesUseCase.getConversionRates(code)) {
                    is Result.Success -> {
                        _conversionRates = result.data
                        _rateRefreshState.value = RatesUIState(
                            isLoading = false,
                            status = Constants.API_SUCCESS
                        )
                    }
                    is Result.Error -> {
                        _rateRefreshState.value = RatesUIState(
                            isLoading = false,
                            status = Constants.API_ERROR,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    private fun calculateConversionAmount(isTargetAmtChanged: Boolean = false) {
        if (_baseCountyCode != null && _targetCountyCode != null) {
            val rate = _conversionRates?.get(_targetCountyCode)
            if (rate != null) {
                _paramChangeLiveData.value = if (isTargetAmtChanged ) {
                    _toAmount.value?.let { amt ->
                        ConverterUIState(
                            isLoading = false,
                            isFromAmtResult = true,
                            result = AppUtility.calculateConversion(amt,rate,isTargetAmtChanged)
                        )
                    }?:  ConverterUIState(isLoading = false, errorMessage = "Something went wrong!!")
                }else{
                    _fromAmount.value?.let { amt ->
                        ConverterUIState(
                            isLoading = false,
                            isToAmtResult = true,
                            result = AppUtility.calculateConversion(amt,rate,isTargetAmtChanged)
                        )
                    } ?:  ConverterUIState(isLoading = false)
                }
            }
        }
    }
}