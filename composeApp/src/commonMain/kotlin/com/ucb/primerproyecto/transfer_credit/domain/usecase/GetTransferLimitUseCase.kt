package com.ucb.primerproyecto.transfer_credit.domain.usecase

import com.ucb.primerproyecto.transfer_credit.domain.repository.TransferCreditRepository

class GetTransferLimitUseCase(
    val repository: TransferCreditRepository
) {
    suspend fun invoke():Double{
        return repository.getLimitTransfer()
    }
}