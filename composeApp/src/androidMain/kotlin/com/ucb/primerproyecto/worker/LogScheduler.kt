package com.ucb.primerproyecto.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager


class LogScheduler(
    private val context: Context
) {
    private val LOG_WORKNAME = "logUploadWork"
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
    }
}