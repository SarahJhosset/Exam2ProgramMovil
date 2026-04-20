package com.ucb.primerproyecto.deposit.domain.repository

import com.ucb.primerproyecto.deposit.domain.model.DepositModel

interface DepositRepository {
    suspend fun saveDeposit(deposit: DepositModel)
}