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

    companion object {
        private const val TAG = "CacheCleanupWorker"
        private const val CHANNEL_ID = "cache_cleanup_channel"
        private const val CHANNEL_NAME = "Cache Cleanup"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "🧹 Iniciando limpieza de caché...")

        return try {
            val dao = db.getDao()


            dao.insert(DollarEntity(dollarOfficial = "6.96", dollarParallel = "9.96", timestamp = System.currentTimeMillis() - 5000))
            dao.insert(DollarEntity(dollarOfficial = "7.00", dollarParallel = "10.00", timestamp = System.currentTimeMillis() - 4000))
            dao.insert(DollarEntity(dollarOfficial = "7.50", dollarParallel = "10.50", timestamp = System.currentTimeMillis() - 3000))
            dao.insert(DollarEntity(dollarOfficial = "7.80", dollarParallel = "10.80", timestamp = System.currentTimeMillis() - 2000))
            dao.insert(DollarEntity(dollarOfficial = "8.00", dollarParallel = "11.00", timestamp = System.currentTimeMillis() - 1000))
            Log.d(TAG, "📝 5 registros de prueba insertados")


            // 1. Leer límite desde Remote Config
            // ✅ CÓDIGO NUEVO — espera correctamente el callback
            val remoteConfig = RemoteConfigManager()
            val maxRecords = kotlinx.coroutines.suspendCancellableCoroutine<Long> { continuation ->
                remoteConfig.fetchConfig { success ->
                    val value = if (success) {
                        remoteConfig.getMaxLocalRecords()
                    } else {
                        10L
                    }
                    Log.d(TAG, "✅ Remote Config cargado: max=$value")
                    continuation.resume(value)
                }
            }

            // 2. Contar registros actuales
            val currentCount = dao.count()
            Log.d(TAG, "📊 Registros actuales: $currentCount / máximo: $maxRecords")

            if (currentCount <= maxRecords) {
                Log.d(TAG, "✅ No se necesita limpieza")
                showNotification(
                    title = "Caché en orden",
                    body = "Tienes $currentCount registros (límite: $maxRecords). No se eliminó nada."
                )
                return Result.success()
            }

            // 3. Calcular cuántos borrar
            val toDelete = currentCount - maxRecords.toInt()
            Log.d(TAG, "🗑️ Borrando $toDelete registros más antiguos...")

            dao.deleteOldest(toDelete)

            val remaining = dao.count()
            Log.d(TAG, "✅ Limpieza completada. Quedan $remaining registros")

            // 4. Notificación de resumen
            showNotification(
                title = "Limpieza completada",
                body = "Se eliminaron $toDelete registros antiguos. Quedan $remaining de $maxRecords permitidos."
            )

            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "❌ Error en limpieza: ${e.message}")
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