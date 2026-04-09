package com.ucb.primerproyecto.dollar.domain.usecase

import com.ucb.primerproyecto.dollar.domain.model.DollarModel
import com.ucb.primerproyecto.dollar.domain.repository.DollarRepository

class GetDollarListUsecase(
    val repository: DollarRepository
) {

    suspend fun invoke(): List<DollarModel> {
        return repository.getList()
    }
}