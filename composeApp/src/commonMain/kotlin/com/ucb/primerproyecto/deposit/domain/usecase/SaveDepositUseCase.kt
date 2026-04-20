package com.ucb.primerproyecto.deposit.domain.usecase

import com.ucb.primerproyecto.deposit.domain.model.DepositModel
import com.ucb.primerproyecto.deposit.domain.repository.DepositRepository

class SaveDepositUseCase(
    private val repository: DepositRepository
) {
    suspend operator fun invoke(model: DepositModel){
        repository.saveDeposit(model)
    }
}