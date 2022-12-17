package com.example.androidclass.domain.repository

import com.example.androidclass.domain.exceptions.AuthException
import com.example.androidclass.domain.exceptions.PasswordMismatchException
import com.example.androidclass.domain.exceptions.UserAlreadyExistsException
import com.example.androidclass.domain.model.SignUpData
import com.example.androidclass.domain.model.User

interface AuthRepository {
    @kotlin.jvm.Throws(UserAlreadyExistsException::class)
    suspend fun signUp(signUpData: SignUpData): Long

    @kotlin.jvm.Throws(AuthException::class, PasswordMismatchException::class)
    suspend fun login(username: String, password: String): User

    @kotlin.jvm.Throws(UserAlreadyExistsException::class)
    suspend fun update(user: User)
}
