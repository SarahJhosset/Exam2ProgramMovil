package com.ucb.primerproyecto.portafolio.domain.usecase

import com.ucb.primerproyecto.deposit.domain.model.DepositModel
import com.ucb.primerproyecto.portafolio.domain.repository.PortafolioRepository

class GetPortafolioUseCase(
    private val repository: PortafolioRepository
) {
    operator fun invoke(onResult: (List<DepositModel>) -> Unit){
        repository.getDeposits  (onResult)
    }
}