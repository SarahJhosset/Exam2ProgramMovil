package com.ucb.primerproyecto.portafolio.presentation.state

sealed class PortafolioEffect {

    // 👉 navegar a deposit
    object NavigateToDeposit : PortafolioEffect()

    // 👉 mostrar error
    data class ShowError(val message: String) : PortafolioEffect()

}