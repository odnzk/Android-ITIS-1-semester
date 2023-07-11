package com.example.androidclass.data.repository

import android.content.Context
import com.example.androidclass.domain.repository.AppSettings

class AppSettingsImpl(appContext: Context) : AppSettings {
    private val sp = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getCurrentAccountId(): Long {
        return sp.getLong(PREF_USER_ID_KEY, AppSettings.NO_ACCOUNT_ID)
    }

    override fun setCurrentAccountId(accountId: Long) {
        sp.edit().putLong(PREF_USER_ID_KEY, accountId).apply()
    }

    companion object {
        private const val PREF_USER_ID_KEY = "userIdKey"
    }
}
