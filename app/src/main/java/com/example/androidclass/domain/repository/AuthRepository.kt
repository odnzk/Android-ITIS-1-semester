package com.example.androidclass.domain.repository

import com.example.androidclass.domain.exceptions.AuthException
import com.example.androidclass.domain.exceptions.PasswordMismatchException
import com.example.androidclass.domain.exceptions.UserAlreadyExistsException
import com.example.androidclass.domain.model.SignUpData
import com.example.androidclass.domain.model.User

interface AuthRepository {
    @Throws(UserAlreadyExistsException::class)
    suspend fun signUp(signUpData: SignUpData): Long

    @Throws(AuthException::class, PasswordMismatchException::class)
    suspend fun login(username: String, password: String): User

    @Throws(UserAlreadyExistsException::class)
    suspend fun update(user: User)

    @Throws(AuthException::class)
    suspend fun getUserById(id: Long): User

    @Throws(UserAlreadyExistsException::class)
    suspend fun updateUsername(id: Long, username: String)
}
