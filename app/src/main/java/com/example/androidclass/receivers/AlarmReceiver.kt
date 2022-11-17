package com.example.androidclass.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.androidclass.MainActivity
import com.example.androidclass.MainFragment.Companion.KEY_NOTIF_ADDITIONAL_TEXT
import com.example.androidclass.MainFragment.Companion.KEY_NOTIF_CONTENT
import com.example.androidclass.MainFragment.Companion.KEY_NOTIF_ID
import com.example.androidclass.MainFragment.Companion.KEY_NOTIF_TITLE
import com.example.androidclass.util.NotificationProvider

class AlarmReceiver : BroadcastReceiver() {

    private var notificationProvider: NotificationProvider? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.run {
            val notificationId = getInt(KEY_NOTIF_ID)
            val title = getString(KEY_NOTIF_TITLE)
            val content = getString(KEY_NOTIF_CONTENT)
            val additionalText = getString(KEY_NOTIF_ADDITIONAL_TEXT)

            val flag =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                flag
            )
            notificationProvider?.showNotification(
                id = notificationId,
                title = title,
                contentText = content,
                additionalText = additionalText,
                pendingIntent
            )
        }
    }
}
