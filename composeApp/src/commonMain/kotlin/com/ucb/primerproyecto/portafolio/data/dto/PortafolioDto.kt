package com.ucb.primerproyecto.portafolio.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PortafolioDto(
    val totalBalance: Float,
    val percentIncrese: Float,
    val depositFiat: Float,
    val timestamp: Long
)