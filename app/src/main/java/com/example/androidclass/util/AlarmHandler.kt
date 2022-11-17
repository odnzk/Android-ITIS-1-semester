package com.example.androidclass.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.content.getSystemService
import com.example.androidclass.MainFragment
import com.example.androidclass.receivers.AlarmReceiver

class AlarmHandler(private val context: Context) {
    private val alarmManager = context.getSystemService() as? AlarmManager
    private val notificationProvider = NotificationProvider(context)

    fun setAlarm(
        seconds: Long, id: Int, title: String, content: String, additionalText: String? = null
    ) {
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(MainFragment.KEY_NOTIF_ID, id)
            putExtra(MainFragment.KEY_NOTIF_TITLE, title)
            putExtra(MainFragment.KEY_NOTIF_CONTENT, content)
            putExtra(MainFragment.KEY_NOTIF_ADDITIONAL_TEXT, additionalText)
        }.let { intent -> PendingIntent.getBroadcast(context, 0, intent, flag) }

        alarmManager?.set(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + seconds * 1000,
            intent
        )
    }

    fun cancelAlarm(lastNotificationId: Int) {
        lastNotificationId?.let { notificationProvider.cancelNotification(lastNotificationId) }
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager?.cancel(pendingIntent)
    }
}
