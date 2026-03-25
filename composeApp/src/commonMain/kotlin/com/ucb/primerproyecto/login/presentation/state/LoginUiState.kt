package com.ucb.primerproyecto.login.presentation.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)