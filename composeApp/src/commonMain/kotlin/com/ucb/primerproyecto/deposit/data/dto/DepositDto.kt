package com.ucb.primerproyecto.deposit.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DepositDto(
    val amount: Double = 0.0,
    val currency: String = "",
    val timestamp: Long = 0L
)