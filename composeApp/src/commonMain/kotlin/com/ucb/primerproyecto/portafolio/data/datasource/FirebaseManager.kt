package com.ucb.primerproyecto.portafolio.data.datasource

import com.ucb.primerproyecto.deposit.domain.model.DepositModel

expect class FirebaseManager() {

    fun observeDeposits(onResult: (List<DepositModel>) -> Unit)

}