package com.ucb.primerproyecto.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.primerproyecto.backup.data.datasource.BackupFirebaseDataSourceImpl
import com.ucb.primerproyecto.dollar.domain.usecase.GetDollarListUsecase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BackupWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val getDollarListUseCase: GetDollarListUsecase by inject()

    companion object {
        private const val TAG = "BackupWorker"
        private const val CHANNEL_ID   = "backup_channel"
        private const val CHANNEL_NAME = "Backup Notifications"
        // max de intentos
        const val MAX_RETRIES = 3
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "Iniciando backup nocturno... Intento ${runAttemptCount + 1}/$MAX_RETRIES")

        return try {
            val dollars = getDollarListUseCase.invoke()
            Log.d(TAG, "Datos leídos de Room: ${dollars.size} registros de dólares")

            val firebaseDataSource = BackupFirebaseDataSourceImpl()
            val success = firebaseDataSource.backupDollars(dollars)

            if (success) {
                Log.d(TAG, "Backup exitoso: ${dollars.size} registros")

                showNotification(
                    title = "Backup completado",
                    body  = "Se respaldaron ${dollars.size} registros en Firebase"
                )
                Result.success()

            } else {
                throw Exception("Firebase devolvió false en el backup")
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error en backup intento ${runAttemptCount + 1}: ${e.message}")

            if (runAttemptCount < MAX_RETRIES - 1) {
                Log.d(TAG, "Reintentando backup...")
                Result.retry()
            } else {
                Log.e(TAG, "Backup fallido después de $MAX_RETRIES intentos")

                showNotification(
                    title = "Backup fallido",
                    body  = "No se pudo completar el respaldo después de $MAX_RETRIES intentos: ${e.message}"
                )
                Result.failure()
            }
        }
    }

    private fun showNotification(title: String, body: String) {
        val manager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_upload_done)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // ID notificaciones
        manager.notify(2001, notification)
    }
}