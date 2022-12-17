package com.example.androidclass.presentation.util

import com.example.androidclass.domain.exceptions.EmptyFieldException
import com.example.androidclass.domain.exceptions.InvalidField
import com.example.androidclass.domain.model.SignUpData

class UserValidator {

    @kotlin.jvm.Throws(EmptyFieldException::class, InvalidField::class)
    fun validate(signUpData: SignUpData): Boolean {
        if (signUpData.username.isBlank()) throw EmptyFieldException(Field.USERNAME)
        if (signUpData.password.isBlank()) throw EmptyFieldException(Field.PASSWORD)
        if (signUpData.username.length !in Field.USERNAME.acceptableLen) throw InvalidField(Field.USERNAME)
        if (signUpData.password.length !in Field.PASSWORD.acceptableLen) throw InvalidField(Field.PASSWORD)
        return true
    }
}

enum class Field(val acceptableLen: IntRange) {
    USERNAME(5..20), PASSWORD(8..20)
}
