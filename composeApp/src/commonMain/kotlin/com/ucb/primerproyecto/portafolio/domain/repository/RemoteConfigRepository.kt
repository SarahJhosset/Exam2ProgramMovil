package com.ucb.primerproyecto.portafolio.domain.repository

interface RemoteConfigRepository {

    fun fetchConfig(onResult: (Boolean) -> Unit)

    fun isMaintenanceMode(): Boolean

    fun isDepositEnabled(): Boolean

    fun getMinVersion(): String
}