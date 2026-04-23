
/*
package com.ucb.primerproyecto

import android.app.Application
import com.ucb.primerproyecto.di.getModules
import com.ucb.primerproyecto.worker.LogScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
class AndroidApp: Application() {

    override fun onCreate() {
        super.onCreate()
        // Configura el WorkManager
        LogScheduler(this).schedulePeriodicaUpload()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AndroidApp)
            modules(getModules())
        }
    }
}
*/



package com.ucb.primerproyecto

import android.app.Application
import com.ucb.primerproyecto.di.getModules
import com.ucb.primerproyecto.translation.LocalTranslationService
import com.ucb.primerproyecto.translation.TranslationRepository
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

        currentLocale = deviceLocale

        LogScheduler(this).schedulePeriodicaUpload()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AndroidApp)
            modules(getModules())
        }
    }

    companion object {
        // Locale actual desde FirebaseService
        var currentLocale: String = "es"
    }
}

