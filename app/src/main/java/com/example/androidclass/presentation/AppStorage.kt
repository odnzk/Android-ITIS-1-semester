package com.example.androidclass.presentation

import android.content.Context
import com.example.androidclass.data.AppDatabase
import com.example.androidclass.data.repository.AppSettingsImpl
import com.example.androidclass.data.repository.AuthUserRepositoryImpl
import com.example.androidclass.domain.repository.AppSettings
import com.example.androidclass.domain.repository.AuthRepository

class AppStorage(context: Context) {
    private val authRepository: AuthRepository by lazy {
        AuthUserRepositoryImpl(AppDatabase.getInstance(context).userDao())
    }

    private val appSettings: AppSettings by lazy {
        AppSettingsImpl(context)
    }



}
