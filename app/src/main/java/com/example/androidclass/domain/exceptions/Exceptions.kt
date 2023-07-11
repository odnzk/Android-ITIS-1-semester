package com.example.androidclass.domain.exceptions

import com.example.androidclass.presentation.util.Field

open class AppException : RuntimeException()

class EmptyFieldException(
    val field: Field
) : AppException()

class InvalidField(
    val field: Field
) : AppException()

class PasswordMismatchException : AppException()

class UserAlreadyExistsException : AppException()

class AuthException : AppException()

class StorageException : AppException()


