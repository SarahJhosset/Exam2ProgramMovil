package com.ucb.primerproyecto.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.primerproyecto.core.data.notification.FirebaseService
import com.ucb.primerproyecto.translation.LocalTranslationService

class NotificationTranslationWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            val titleKey = inputData.getString("title_key") ?: return Result.failure()
            val bodyKey  = inputData.getString("body_key")  ?: return Result.failure()
            val locale   = inputData.getString("locale")    ?: "es"

            Log.d("TranslationWorker", "Traduciendo: $titleKey / $bodyKey para locale: $locale")

            val title = LocalTranslationService.get(titleKey, locale)
            val body  = LocalTranslationService.get(bodyKey, locale)

            Log.d("TranslationWorker", "Resultado: $title / $body")

            // noti traducida
            FirebaseService.showNotification(applicationContext, title, body)

            Result.success()

        } catch (e: Exception) {
            Log.e("TranslationWorker", "Error al traducir notificación", e)
            Result.retry()
        }
    }
}