package com.ucb.primerproyecto.portafolio.data.repository

import com.ucb.primerproyecto.deposit.domain.model.DepositModel
import com.ucb.primerproyecto.portafolio.data.datasource.FirebaseManager
import com.ucb.primerproyecto.portafolio.domain.repository.PortafolioRepository

class PortafolioRepositoryImpl(
    private val firebase: FirebaseManager
) : PortafolioRepository {

    override fun getDeposits(onResult: (List<DepositModel>) -> Unit) {
        firebase.observeDeposits(onResult)
    }
}