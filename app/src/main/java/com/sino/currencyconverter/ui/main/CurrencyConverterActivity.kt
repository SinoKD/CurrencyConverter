package com.sino.currencyconverter.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sino.currencyconverter.R

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrencyConverterFragment.newInstance())
                .commitNow()
        }
    }
}