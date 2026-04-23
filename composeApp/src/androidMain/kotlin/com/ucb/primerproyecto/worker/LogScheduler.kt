package com.ucb.primerproyecto.worker

import android.content.Context
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

class LogScheduler(
    private val context: Context
) {
    private val LOG_WORKNAME = "logUploadWork"
    private val BACKUP_WORKNAME = "nocturnalBackupWork"
    private val INTERVAL_MINUTES = 15L


    fun schedulePeriodicaUpload() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val logRequest = PeriodicWorkRequest.Builder(
            LogUploadWorker::class.java,
            INTERVAL_MINUTES,
            TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(
                LOG_WORKNAME,
                ExistingPeriodicWorkPolicy.KEEP,
                logRequest
            )
        scheduleNocturnalBackup()
    }

    private fun scheduleNocturnalBackup() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // cuanto para las 2:00
        val now    = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 2)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            // mas de las 2
            if (before(now)) add(Calendar.DAY_OF_MONTH, 1)
        }
        val initialDelayMinutes = (target.timeInMillis - now.timeInMillis) / 60000

        val backupRequest = PeriodicWorkRequest.Builder(
            BackupWorker::class.java,
            24L,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInitialDelay(initialDelayMinutes, TimeUnit.MINUTES)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                10L,
                TimeUnit.MINUTES
            )
            .build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(
                BACKUP_WORKNAME,
                ExistingPeriodicWorkPolicy.KEEP,
                backupRequest
            )

        println("Backup nocturno programado en $initialDelayMinutes minutos")
    }
}
