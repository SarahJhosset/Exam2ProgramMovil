package com.ucb.primerproyecto.dollar.data.repository

import com.ucb.primerproyecto.dollar.data.datasource.DollarLocalDataSource
import com.ucb.primerproyecto.dollar.data.mapper.toEntity
import com.ucb.primerproyecto.dollar.data.mapper.toModel
import com.ucb.primerproyecto.dollar.domain.model.DollarModel
import com.ucb.primerproyecto.dollar.domain.repository.DollarRepository

class DollarRepositoryImpl(
    val localDataSource: DollarLocalDataSource
): DollarRepository {
    override suspend fun save(model: DollarModel) {
        localDataSource.save(model.toEntity())
    }

    override suspend fun getList(): List<DollarModel> {
        return localDataSource.list().map {
            it.toModel()
        }
    }
}