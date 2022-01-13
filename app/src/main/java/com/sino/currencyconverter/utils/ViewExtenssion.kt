package com.sino.currencyconverter.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textChangedByUser(textChangedByUser: (String, Boolean) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            textChangedByUser.invoke(editable.toString(), hasFocus())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}