package com.ucb.primerproyecto.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.primerproyecto.dollar.domain.usecase.GetDollarListUsecase
import com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig.RemoteConfigManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DynamicSyncWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val getDollarListUseCase: GetDollarListUsecase by inject()

    companion object {
        private const val TAG = "DynamicSyncWorker"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "🔄 Ejecutando sincronización dinámica...")

        return try {
            // 1. Leer intervalo actual desde Remote Config
            val remoteConfig = RemoteConfigManager()
            remoteConfig.fetchConfig { success ->
                if (success) {
                    val interval = remoteConfig.getSyncIntervalMinutes()
                    Log.d(TAG, "⏱️ Intervalo actual: $interval minutos")

                    // 2. Reprogramar con el nuevo intervalo
                    DynamicSyncScheduler(applicationContext)
                        .schedule(interval)
                }
            }

            // 3. Hacer la sincronización real
            val dollars = getDollarListUseCase.invoke()
            Log.d(TAG, "✅ Sincronizados ${dollars.size} registros")

            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "❌ Error en sincronización: ${e.message}")
            Result.retry()
        }
    }
}