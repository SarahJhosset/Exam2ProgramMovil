package com.ucb.primerproyecto.user_balance.domain.usecase

import com.ucb.primerproyecto.user_balance.domain.model.UserBalanceModel
import com.ucb.primerproyecto.user_balance.domain.repository.UserBalanceRepository

class GetUserBalanceUseCase(
    val repository: UserBalanceRepository
) {
    suspend fun invoke(): UserBalanceModel{
        return  repository.getUserBalance()
    }
}