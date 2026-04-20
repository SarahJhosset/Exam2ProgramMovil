package com.ucb.primerproyecto.deposit.data.datasource

import com.ucb.primerproyecto.deposit.data.dto.DepositDto

expect class DepositFirebaseManager (){
    suspend fun saveDeposit(deposit: DepositDto)
}