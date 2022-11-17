package com.example.androidclass.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.androidclass.storage.ObservableStorage

class AirplaneModeChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.getBooleanExtra("state", false)?.let {
            ObservableStorage.isAirplaneModeOn = it
            ObservableStorage.notifyObservers()
        }
    }

}
