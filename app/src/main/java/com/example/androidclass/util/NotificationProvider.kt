package com.example.androidclass.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.androidclass.R

class NotificationProvider(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun cancelNotification(id: Int) {
        notificationManager.cancel(id)
    }

    fun showNotification(
        id: Int,
        title: String?,
        contentText: String?,
        additionalText: String? = null,
        pendingIntent: PendingIntent
    ) {
        val vibrations = longArrayOf(100, 200)
        val notifAudioAttributes =
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION).build()

        val notifSound =
            Uri.parse("android.resource://" + context.packageName + "/" + R.raw.alarm_clock)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                context.getString(R.string.default_notification_channel_id),
                context.getString(R.string.default_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.GREEN
                vibrationPattern = vibrations
                setSound(notifSound, notifAudioAttributes)
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            context, context.getString(R.string.default_notification_channel_id)
        ).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title)
            .setContentText(contentText).setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setChannelId(context.getString(R.string.default_notification_channel_id))
            .setSound(notifSound)
            .setContentIntent(pendingIntent).setLights(Color.GREEN, 100, 500).apply {
                if (additionalText != null) {
                    setStyle(
                        NotificationCompat.BigTextStyle().bigText(additionalText)
                    )
                }
            }
        notificationManager.notify(id, builder.build())
    }

}
