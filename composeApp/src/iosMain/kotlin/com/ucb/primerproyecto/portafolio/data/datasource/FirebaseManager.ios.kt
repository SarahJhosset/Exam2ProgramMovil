package com.ucb.primerproyecto.portafolio.data.datasource

import com.ucb.primerproyecto.deposit.domain.model.DepositModel

actual class FirebaseManager actual constructor() {

    actual fun observeDeposits(onResult: (List<DepositModel>) -> Unit) {
        // iOS no tiene Firebase RTDB en KMP — devuelve lista vacía
        onResult(emptyList())
    }
}