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

    actual fun getSyncIntervalMinutes(): Long {
        val value = remoteConfig.getLong("sync_interval_minutes")
        println("⏱️ RC sync_interval_minutes = $value")
        return if (value > 0) value else 15L
    }

    actual fun getMaxLocalRecords(): Long {
        val value = remoteConfig.getLong("max_local_records")
        println("🗑️ RC max_local_records = $value")
        return if (value > 0) value else 10L
    }

}