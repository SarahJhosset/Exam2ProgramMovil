package com.ucb.primerproyecto.portafolio.presentation.state
sealed class PortafolioEvent {
    object OnRefresh : PortafolioEvent()
    data class OnCoinClick(val coin: String) : PortafolioEvent()
    object OnAddClick : PortafolioEvent()
}