package com.ucb.primerproyecto.deposit.presentation.state
sealed class DepositEvent {
    data class OnAmountChange(val value: String) : DepositEvent()
    object OnSaveDeposit : DepositEvent()
}