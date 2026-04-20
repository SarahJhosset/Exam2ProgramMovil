package com.ucb.primerproyecto.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class LogScheduler(
    private val context: Context
) {

    private val WORK_NAME = "logUploadWork"

    fun schedule() {

        val request = PeriodicWorkRequestBuilder<LogUploadWorker>(
            1, TimeUnit.MINUTES
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }
}