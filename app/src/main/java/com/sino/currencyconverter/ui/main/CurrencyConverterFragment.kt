package com.sino.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.sino.currencyconverter.databinding.MainFragmentBinding
import com.sino.currencyconverter.utils.Constants
import com.sino.currencyconverter.utils.textChangedByUser
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrencyConverterFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyConverterFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val viewModel: ConverterViewModel by viewModels()

    private val binding get() = _binding!!

    private var fromCodeAdapter: ArrayAdapter<String>? = null
    private var toCodeAdapter: ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCountryCodes()

        binding.edFromAmount.textChangedByUser { text, hasFocus ->
            if (hasFocus) {
                viewModel.fromAmountChanged(text.toDoubleOrNull())
            }
        }

        binding.edToAmount.textChangedByUser { text, hasFocus ->
            if (hasFocus) {
                viewModel.toAmountChanged(text.toDoubleOrNull())
            }
        }

        binding.tvFromCountries.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                viewModel.setBaseCountryCode(fromCodeAdapter?.getItem(position))
            }

        binding.tvToCountries.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                viewModel.setTargetCountryCode(toCodeAdapter?.getItem(position))
                viewModel.fromAmountChanged(binding.edFromAmount.text.toString().toDoubleOrNull())
            }

        viewModel.countryCodes.observe(viewLifecycleOwner, { codesState ->
            binding.pbLoader.visibility = if (codesState.isLoading) View.VISIBLE else View.GONE
            if (codesState.errorMessage != null) {
                showRetrySnackBar(
                    message = codesState.errorMessage,
                    retryFunction = { getCountryCodes() })
            } else if (codesState.result != null) {
                fromCodeAdapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    codesState.result.map {
                        it[0]
                    }
                )
                toCodeAdapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    codesState.result.map {
                        it[0]
                    }
                )
                binding.tvFromCountries.setAdapter(fromCodeAdapter)
                binding.tvToCountries.setAdapter(toCodeAdapter)
            }
        })
        viewModel.rateRefreshState.observe(viewLifecycleOwner, { item ->
            binding.pbLoader.visibility = if (item.isLoading) View.VISIBLE else View.GONE
            if (item.errorMessage != null) {
                showInfoSnackBar(item.errorMessage)
            } else if (item.status != null && item.status == Constants.API_SUCCESS) {
                viewModel.fromAmountChanged(binding.edFromAmount.text.toString().toDoubleOrNull())
            }
        })
        viewModel.paramChangeLiveData.observe(viewLifecycleOwner, { item ->
            if (item.errorMessage != null) {
                showInfoSnackBar(item.errorMessage)
            } else {
                if (item.isToAmtResult && item.result != null) {
                    binding.edToAmount.setText(item.result)
                }
                if (item.isFromAmtResult && item.result != null) {
                    binding.edFromAmount.setText(item.result)
                }
            }
        })
    }

    private fun getCountryCodes() {
        viewModel.getCountryCodes()
    }

    private fun showRetrySnackBar(
        message: String,
        action: String = "Retry",
        retryFunction: () -> Unit
    ) {
        Snackbar.make(binding.mainLayout, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(action) { retryFunction() }
            .show()
    }

    private fun showInfoSnackBar(message: String) {
        Snackbar.make(binding.mainLayout, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}