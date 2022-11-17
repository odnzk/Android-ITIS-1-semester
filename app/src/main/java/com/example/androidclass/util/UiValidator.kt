package com.example.androidclass.util

import android.widget.EditText

object UiValidator {

    fun validateEditText(vararg args : EditText): Boolean {
        for (ed in args){
            if(ed.text.isNullOrBlank()){
                return false
            }
        }
        return true
    }

    fun isTextValid(errorMessage: String, vararg array: EditText): Boolean {
        var isValid = true
        array.forEach {
            if (it.text.isNullOrBlank()) {
                it.error = errorMessage
                isValid = false
            }
        }
        return isValid
    }
}
