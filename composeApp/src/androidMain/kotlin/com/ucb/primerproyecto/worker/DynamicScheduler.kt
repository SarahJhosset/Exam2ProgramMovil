package com.ucb.primerproyecto.worker

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig.RemoteConfigManager
import java.util.concurrent.TimeUnit

class DynamicScheduler(
    private val context: Context,
    private val remoteConfigManager: RemoteConfigManager
) {
    companion object {
        private const val TAG       = "DynamicScheduler"
        private const val SYNC_WORK = "dynamicSync"
    }

    // Llama a esto DESPUÉS de que Remote Config haya cargado
    fun scheduleSyncWithRemoteInterval() {

        remoteConfigManager.fetchConfig { success ->
            if (!success) {
                Log.w(TAG, "Remote Config falló, usando intervalo por defecto: 15 min")
            }

            // Lee el intervalo definido en Remote Config
            val intervalMinutes = remoteConfigManager.getSyncIntervalMinutes()
            Log.d(TAG, "Intervalo de sincronización: $intervalMinutes minutos")

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = PeriodicWorkRequest.Builder(
                SyncWorker::class.java,
                intervalMinutes,
                TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()

            // REPLACE cancela el Worker anterior y crea uno nuevo con el nuevo intervalo
            // Esto es lo que hace que el cambio en Remote Config surta efecto
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    SYNC_WORK,
                    ExistingPeriodicWorkPolicy.REPLACE,  // ← clave: reemplaza el anterior
                    syncRequest
                )

            Log.d(TAG, "✅ SyncWorker programado cada $intervalMinutes minutos")
        }
    }
}