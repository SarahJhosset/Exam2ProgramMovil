package com.ucb.primerproyecto.portafolio.presentation.state

import com.ucb.primerproyecto.deposit.domain.model.DepositModel

data class PortafolioUiState(
    val totalBalance: Double = 0.0,
    val deposits: List<DepositModel> = emptyList(),
    val isLoading: Boolean = true,
    val depositEnabled: Boolean = true,   // 👈 NUEVO
    val isMaintenance: Boolean = false     // 👈 OPCIONAL
)

