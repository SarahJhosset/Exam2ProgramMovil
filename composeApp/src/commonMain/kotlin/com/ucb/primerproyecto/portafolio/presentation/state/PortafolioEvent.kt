package com.ucb.primerproyecto.portafolio.presentation.state

sealed class PortafolioEvent {
    object OnAddClick   : PortafolioEvent()
    object OnDebugClick : PortafolioEvent()
    object OnDollarClick : PortafolioEvent()
}
