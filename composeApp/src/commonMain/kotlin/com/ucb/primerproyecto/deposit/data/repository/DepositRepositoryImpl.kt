package com.ucb.primerproyecto.deposit.data.repository

import com.ucb.primerproyecto.deposit.data.datasource.DepositFirebaseManager
import com.ucb.primerproyecto.deposit.data.mapper.toDto
import com.ucb.primerproyecto.deposit.domain.model.DepositModel
import com.ucb.primerproyecto.deposit.domain.repository.DepositRepository

class DepositRepositoryImpl(
    private val firebase: DepositFirebaseManager
) : DepositRepository {

    override suspend fun saveDeposit(deposit: DepositModel) {
        firebase.saveDeposit(deposit.toDto())
    }
}