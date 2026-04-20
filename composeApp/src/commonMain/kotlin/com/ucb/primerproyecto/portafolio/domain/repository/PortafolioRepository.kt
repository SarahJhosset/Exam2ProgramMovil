package com.ucb.primerproyecto.portafolio.domain.repository

import com.ucb.primerproyecto.deposit.domain.model.DepositModel

interface PortafolioRepository {
    fun getDeposits(onResult: (List<DepositModel>) -> Unit)
}