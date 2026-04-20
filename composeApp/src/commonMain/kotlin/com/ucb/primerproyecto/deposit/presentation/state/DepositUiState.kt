package com.ucb.primerproyecto.deposit.presentation.state
data class DepositUiState(
    val amount: String = "",
    val paidAmount: String = "",
    val currency: String = "USDC",
    val includeFee: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)