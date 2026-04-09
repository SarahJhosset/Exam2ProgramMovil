package com.ucb.primerproyecto.dollar.domain.repository

import com.ucb.primerproyecto.dollar.domain.model.DollarModel

interface DollarRepository {
    suspend fun save(model: DollarModel)
    suspend fun getList(): List<DollarModel>
}