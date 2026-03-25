package com.ucb.primerproyecto.user_balance.domain.repository

import com.ucb.primerproyecto.user_balance.domain.model.UserBalanceModel

interface UserBalanceRepository {
    suspend fun getUserBalance(): UserBalanceModel
}