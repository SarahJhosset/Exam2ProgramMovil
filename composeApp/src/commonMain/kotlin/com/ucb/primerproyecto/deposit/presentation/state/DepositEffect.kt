package com.ucb.primerproyecto.deposit.presentation.state

sealed class DepositEffect {
    object NavigateToHome : DepositEffect()
    data class ShowError(val message: String) : DepositEffect()
    data class ShowSuccess(val message: String) : DepositEffect()
}