package com.ucb.primerproyecto.dollar.domain.usecase

import com.ucb.primerproyecto.dollar.domain.model.DollarModel
import com.ucb.primerproyecto.dollar.domain.repository.DollarRepository

class CreateDollarUseCase(
    private val repository: DollarRepository
) {

    suspend fun invoke(model: DollarModel) {
        repository.save(model)
    }
}