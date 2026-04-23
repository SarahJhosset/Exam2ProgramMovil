package com.ucb.primerproyecto.core.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ucb.primerproyecto.AndroidApp
import com.ucb.primerproyecto.MainActivity
import com.ucb.primerproyecto.worker.NotificationTranslationWorker

class FirebaseService : FirebaseMessagingService() {

    companion object {
        private const val TAG          = "FirebaseService"
        const val CHANNEL_ID           = "default_notification_channel"
        private const val CHANNEL_NAME = "General Notifications"

        fun showNotification(context: Context, title: String, body: String) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                manager.createNotificationChannel(channel)
            }

            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            manager.notify(System.currentTimeMillis().toInt(), notification)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "FCM recibido de: ${remoteMessage.from}")

        val data = remoteMessage.data

        val titleKey = data["title_key"]
        val bodyKey  = data["body_key"]

        if (titleKey != null && bodyKey != null) {
            val workRequest = OneTimeWorkRequestBuilder<NotificationTranslationWorker>()
                .setInputData(
                    workDataOf(
                        "title_key"  to titleKey,
                        "body_key"   to bodyKey,
                        "locale"     to AndroidApp.currentLocale
                    )
                )
                .build()

            WorkManager.getInstance(applicationContext).enqueue(workRequest)
        } else {
            val title = remoteMessage.notification?.title
                ?: data["title"]
                ?: "Notificación"
            val body  = remoteMessage.notification?.body
                ?: data["body"]
                ?: "Tienes un mensaje"

            showNotification(this, title, body)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Token FCM renovado: $token")
    }
}