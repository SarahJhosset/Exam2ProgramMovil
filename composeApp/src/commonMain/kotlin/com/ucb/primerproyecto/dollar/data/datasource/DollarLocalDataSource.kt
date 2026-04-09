package com.ucb.primerproyecto.dollar.data.datasource

import com.ucb.primerproyecto.dollar.data.entity.DollarEntity

interface DollarLocalDataSource {
    suspend fun save(entity: DollarEntity)
    suspend fun list() : List<DollarEntity>
}