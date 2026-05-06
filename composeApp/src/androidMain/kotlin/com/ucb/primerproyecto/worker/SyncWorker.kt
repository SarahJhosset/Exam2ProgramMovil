package com.ucb.primerproyecto.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.primerproyecto.dollar.domain.usecase.GetDollarListUsecase
import com.ucb.primerproyecto.backup.data.datasource.BackupFirebaseDataSourceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val getDollars: GetDollarListUsecase by inject()

    companion object {
        const val TAG = "SyncWorker"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "Iniciando sincronización Room → Firebase...")

        return try {
            // 1. Lee los datos de Room
            val dollars = getDollars.invoke()
            Log.d(TAG, "Registros a sincronizar: ${dollars.size}")

            // 2. Sube a Firebase usando el datasource que ya tienes
            val dataSource = BackupFirebaseDataSourceImpl()
            val success = dataSource.backupDollars(dollars)

            if (success) {
                Log.d(TAG, "✅ Sincronización exitosa: ${dollars.size} registros")
                Result.success()
            } else {
                Log.w(TAG, "⚠️ Firebase devolvió false")
                Result.retry()
            }

        } catch (e: Exception) {
            Log.e(TAG, "❌ Error de sincronización: ${e.message}")
            Result.retry()
        }
    }
}