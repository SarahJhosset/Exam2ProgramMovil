package com.ucb.primerproyecto.transfer_credit.domain.repository

import com.ucb.primerproyecto.transfer_credit.domain.model.TransferHistory

interface TransferCreditRepository {
    suspend fun transferCredit(phone:String, amount:Double):Boolean
    suspend fun getTransferHistory(): List<TransferHistory>
    suspend fun getLimitTransfer():Double

}