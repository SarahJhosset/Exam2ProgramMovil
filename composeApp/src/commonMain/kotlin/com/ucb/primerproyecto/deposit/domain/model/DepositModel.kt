package com.ucb.primerproyecto.deposit.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DepositModel(
    val amount: Double,
    val currency: String,
    val timestamp: Long
)
