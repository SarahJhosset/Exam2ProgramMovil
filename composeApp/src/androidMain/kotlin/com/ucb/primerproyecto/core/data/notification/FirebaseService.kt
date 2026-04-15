package com.ucb.primerproyecto.core.data.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {

    companion object {
        val TAG = FirebaseService::class.java.simpleName
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Data: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d(TAG, "Title: ${it.title}")
            Log.d(TAG, "Body: ${it.body}")
        }
    }
}