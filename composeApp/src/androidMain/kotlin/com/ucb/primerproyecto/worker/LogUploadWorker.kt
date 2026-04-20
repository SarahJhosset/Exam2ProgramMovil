package com.ucb.primerproyecto.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class LogUploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        println("🔥 ejecutando trabajo en background")

        // aquí va tu lógica real (API, DB, etc)

        return Result.success()
    }
}