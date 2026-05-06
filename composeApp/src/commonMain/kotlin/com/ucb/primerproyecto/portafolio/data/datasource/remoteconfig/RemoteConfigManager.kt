package com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig

expect class RemoteConfigManager() {

    fun fetchConfig(onComplete: (Boolean) -> Unit)

    fun isMaintenanceMode(): Boolean

    fun isDepositEnabled(): Boolean

    fun getMinVersion(): String

    fun getSyncIntervalMinutes(): Long

    fun getMaxLocalRecords(): Long

}