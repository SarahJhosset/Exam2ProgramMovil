package com.ucb.primerproyecto.portafolio.presentation.state

data class PortafolioUiState(
    val balanceTotal: Double = 0.0,
    val changePercentage: Double = 0.0,
    val stables: Double = 0.0,
    val ganancias: Double = 0.0,
    val holdings: List<Holding> = emptyList(),
    val isLoading: Boolean = false
)

data class Holding(
    val name: String,
    val amount: Double,
    val price: Double
)