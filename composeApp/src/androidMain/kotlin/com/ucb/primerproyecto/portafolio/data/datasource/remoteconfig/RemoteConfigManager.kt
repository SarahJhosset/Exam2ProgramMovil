package com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

actual class RemoteConfigManager {

    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        // 🔥 IMPORTANTE: evitar cache (para pruebas)
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )
    }

    actual fun fetchConfig(onComplete: (Boolean) -> Unit) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }

    actual fun isMaintenanceMode(): Boolean {
        val value = remoteConfig.getBoolean("maintenance_mode")
        println("🚧 RC maintenance_mode = $value")
        return value
    }

    actual fun isDepositEnabled(): Boolean {
        val value = remoteConfig.getBoolean("deposit_enabled")
        println("💳 RC deposit_enabled = $value")
        return value
    }

    actual fun getMinVersion(): String {
        return remoteConfig.getString("min_version")
    }
}