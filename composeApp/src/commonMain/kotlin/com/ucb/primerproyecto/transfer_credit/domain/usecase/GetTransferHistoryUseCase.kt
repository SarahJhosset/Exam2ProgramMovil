package com.ucb.primerproyecto.transfer_credit.domain.usecase

import com.ucb.primerproyecto.transfer_credit.domain.model.TransferHistory
import com.ucb.primerproyecto.transfer_credit.domain.repository.TransferCreditRepository

class GetTransferHistoryUseCase(
    val repository: TransferCreditRepository
){
    suspend fun invoke():List<TransferHistory>{
        return repository.getTransferHistory()
    }
}