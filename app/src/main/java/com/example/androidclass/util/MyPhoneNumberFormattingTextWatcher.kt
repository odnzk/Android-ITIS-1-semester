package com.example.androidclass.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import java.lang.Integer.min


class MyPhoneNumberFormattingTextWatcher(
    private val etPhone: EditText,
    private val etAnything: EditText,
    private val btnNavigateToSecondFragment: Button
) : TextWatcher {
    private var isFormattingStopped = false
    private var phoneNumber = PhoneNumber()

    companion object {
        const val ET_ANYTHING_MIN_LENGTH = 5
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // if new char is valid -> update phone number
        // else -> stop formatting
        val pairCharAndIndex = getModifiedChar(removeSeparators(s.toString()))
        if (Character.isDigit(pairCharAndIndex.first)) {
            if (count > 0) {
                phoneNumber.add(pairCharAndIndex.second, pairCharAndIndex.first)
            } else {
                phoneNumber.remove(pairCharAndIndex.second)
            }
        } else {
            isFormattingStopped = true
        }
    }

    override fun afterTextChanged(s: Editable?) {
        // if not valid -> remove formatting
        // else ->  change to formatted text
        s?.let { editable ->
            val newEditable: String =
                if (isFormattingStopped) phoneNumber.unformat() else phoneNumber.format()

            etPhone.removeTextChangedListener(this)

            editable.replace(0, s.length, newEditable, 0, newEditable.length)

            etPhone.addTextChangedListener(this)
        }
        etAnything.isEnabled = phoneNumber.isValid()
        btnNavigateToSecondFragment.isEnabled = phoneNumber.isValid()
        etAnything.doAfterTextChanged { editable ->
            editable?.let {
                btnNavigateToSecondFragment.isEnabled =
                    it.trim().length >= ET_ANYTHING_MIN_LENGTH && phoneNumber.isValid()
            }
        }
    }

    private fun getModifiedChar(newEditTextValue: String): Pair<Char, Int> {
        val maxTextValue =
            if (newEditTextValue.length < phoneNumber.unformat().length) phoneNumber.unformat() else newEditTextValue
        val minLength = min(newEditTextValue.length, phoneNumber.unformat().length)
        var index = 0
        while (index < minLength) {
            if (newEditTextValue[index] != phoneNumber.unformat()[index]) {
                return maxTextValue[index] to index
            }
            index++
        }
        return maxTextValue.last() to index
    }

    private fun removeSeparators(str: String): String {
        return str.filter { Character.isDigit(it) }
    }
}

