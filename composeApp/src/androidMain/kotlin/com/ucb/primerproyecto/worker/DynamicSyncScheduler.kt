package com.ucb.primerproyecto.worker

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class DynamicSyncScheduler(private val context: Context) {

    companion object {
        const val WORK_NAME = "dynamicSyncWork"
        private const val TAG = "DynamicSyncScheduler"
    }

    fun schedule(intervalMinutes: Long) {
        Log.d(TAG, "📅 Programando sync cada $intervalMinutes minutos")

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // WorkManager requiere mínimo 15 minutos
        val safeInterval = maxOf(intervalMinutes, 15L)

        val request = PeriodicWorkRequest.Builder(
            DynamicSyncWorker::class.java,
            safeInterval,
            TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        // REPLACE cancela la anterior y programa la nueva
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,  // 👈 clave para el intervalo dinámico
                request
            )

        Log.d(TAG, "✅ Sync programado cada $safeInterval minutos")
    }

    fun cancel() {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        Log.d(TAG, "🛑 Sync cancelado")
    }
}