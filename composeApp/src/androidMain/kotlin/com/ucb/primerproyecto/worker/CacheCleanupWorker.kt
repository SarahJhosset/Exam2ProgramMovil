package com.ucb.primerproyecto.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.primerproyecto.core.data.db.AppDatabase
import com.ucb.primerproyecto.dollar.data.dao.DollarDao
import com.ucb.primerproyecto.dollar.data.entity.DollarEntity
import com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig.RemoteConfigManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.resume

class CacheCleanupWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val db: AppDatabase by inject()
    private val dollarDao: DollarDao by inject()

    companion object {
        private const val TAG = "CacheCleanupWorker"
        private const val CHANNEL_ID = "cache_cleanup_channel"
        private const val CHANNEL_NAME = "Cache Cleanup"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "🧹 Iniciando limpieza de caché...")

        return try {

            val dao = db.getDao()
            dao.insertReplace(DollarEntity(dollarOfficial = "6.96", dollarParallel = "9.96", timestamp = System.currentTimeMillis() - 7000))
            dao.insertReplace(DollarEntity(dollarOfficial = "7.00", dollarParallel = "10.00", timestamp = System.currentTimeMillis() - 6000))
            dao.insertReplace(DollarEntity(dollarOfficial = "7.50", dollarParallel = "10.50", timestamp = System.currentTimeMillis() - 5000))
            dao.insertReplace(DollarEntity(dollarOfficial = "7.80", dollarParallel = "10.80", timestamp = System.currentTimeMillis() - 4000))
            dao.insertReplace(DollarEntity(dollarOfficial = "8.00", dollarParallel = "11.00", timestamp = System.currentTimeMillis() - 3000))
            dao.insertReplace(DollarEntity(dollarOfficial = "7.80", dollarParallel = "10.80", timestamp = System.currentTimeMillis() - 2000))
            dao.insertReplace(DollarEntity(dollarOfficial = "8.00", dollarParallel = "11.00", timestamp = System.currentTimeMillis() - 1000))
            dao.insertReplace(DollarEntity(dollarOfficial = "7.80", dollarParallel = "10.80", timestamp = System.currentTimeMillis() - 8000))
            dao.insertReplace(DollarEntity(dollarOfficial = "8.00", dollarParallel = "11.00", timestamp = System.currentTimeMillis() - 8800))
            dao.insertReplace(DollarEntity(dollarOfficial = "7.80", dollarParallel = "10.80", timestamp = System.currentTimeMillis() - 6700))
            dao.insertReplace(DollarEntity(dollarOfficial = "8.00", dollarParallel = "11.00", timestamp = System.currentTimeMillis() - 1100))
            Log.d(TAG, "📝 11 registros de prueba insertados")
            Log.d(TAG, "📊 Registros actuales: ${dao.count()}")


            // 1. Lee límite desde Remote Config
            val remoteConfig = RemoteConfigManager()
            val maxRecords = kotlinx.coroutines.suspendCancellableCoroutine<Long> { continuation ->
                remoteConfig.fetchConfig { success ->
                    val value = if (success) remoteConfig.getMaxLocalRecords() else 10L
                    Log.d(TAG, "✅ Remote Config: max=$value")
                    continuation.resume(value)
                }
            }

            // 2. Cuenta registros actuales
            val currentCount = dollarDao.count()
            Log.d(TAG, "📊 Registros: $currentCount / máximo: $maxRecords")

            if (currentCount <= maxRecords) {
                Log.d(TAG, "✅ No se necesita limpieza")
                showNotification(
                    title = "Caché en orden",
                    body  = "Tienes $currentCount registros (límite: $maxRecords)"
                )
                return Result.success()
            }

            // 3. Elimina los más antiguos
            val toDelete = currentCount - maxRecords.toInt()
            dollarDao.deleteOldest(toDelete)

            val remaining = dollarDao.count()
            Log.d(TAG, "✅ Limpieza: eliminados $toDelete, quedan $remaining")

            showNotification(
                title = "Limpieza completada",
                body  = "Se eliminaron $toDelete registros. Quedan $remaining de $maxRecords."
            )

            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "❌ Error: ${e.message}")
            Result.failure()
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
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        manager.notify(3001, notification)
        Log.d(TAG, "🔔 Notificación enviada: $title")
    }


}