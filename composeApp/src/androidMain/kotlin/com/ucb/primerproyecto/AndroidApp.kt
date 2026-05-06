
package com.ucb.primerproyecto

import android.app.Application
import com.ucb.primerproyecto.di.getModules
import com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig.RemoteConfigManager
import com.ucb.primerproyecto.translation.LocalTranslationService
import com.ucb.primerproyecto.translation.TranslationRepository
import com.ucb.primerproyecto.worker.DynamicScheduler
import com.ucb.primerproyecto.worker.DynamicSyncScheduler
import com.ucb.primerproyecto.worker.LogScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import java.util.Locale

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val deviceLocale = Locale.getDefault().language  // "es" o "en"

        LocalTranslationService.load("en", TranslationRepository.getAll("en"))
        LocalTranslationService.load("es", TranslationRepository.getAll("es"))

        AndroidApp.currentLocale = deviceLocale

        LogScheduler(this).schedulePeriodicaUpload()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AndroidApp)
            modules(getModules())
        }

        val remoteConfig = org.koin.java.KoinJavaComponent
            .getKoin().get<RemoteConfigManager>()

        DynamicScheduler(this, remoteConfig)
            .scheduleSyncWithRemoteInterval()


        /*
        // Leer intervalo desde Remote Config y programar
        val remoteConfig = RemoteConfigManager()
        remoteConfig.fetchConfig { success ->
            val interval = if (success) {
                remoteConfig.getSyncIntervalMinutes()
            } else {
                15L // valor por defecto si falla
            }
            DynamicSyncScheduler(this).schedule(interval)
         */

    }

    companion object {
        // Locale actual desde FirebaseService
        var currentLocale: String = "es"
    }
}
