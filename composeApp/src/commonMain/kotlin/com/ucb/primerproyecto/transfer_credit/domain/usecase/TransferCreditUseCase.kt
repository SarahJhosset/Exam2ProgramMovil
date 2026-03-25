package com.ucb.primerproyecto.transfer_credit.domain.usecase

import com.ucb.primerproyecto.transfer_credit.domain.repository.TransferCreditRepository

class TransferCreditUseCase(
    val repository: TransferCreditRepository
)  {
    suspend fun invoke(phone:String,amount:Double):Boolean{
        return repository.transferCredit(phone,amount)
    }

}