package com.ucb.primerproyecto.portafolio.data.repository

import com.ucb.primerproyecto.portafolio.data.datasource.remoteconfig.RemoteConfigManager
import com.ucb.primerproyecto.portafolio.domain.repository.RemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val remoteConfig: RemoteConfigManager
) : RemoteConfigRepository {

    override fun fetchConfig(onResult: (Boolean) -> Unit) =
        remoteConfig.fetchConfig(onResult)

    override fun isMaintenanceMode() =
        remoteConfig.isMaintenanceMode()

    override fun isDepositEnabled() =
        remoteConfig.isDepositEnabled()

    override fun getMinVersion() =
        remoteConfig.getMinVersion()
}