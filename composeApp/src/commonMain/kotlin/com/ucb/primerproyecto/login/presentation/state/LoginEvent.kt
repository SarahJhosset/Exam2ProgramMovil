package com.ucb.primerproyecto.login.presentation.state

sealed interface LoginEvent {
    object OnClick: LoginEvent
    data class OnEmailChanged(
        val value: String
    ): LoginEvent
    data class OnPasswordChanged(
        val value: String
    ): LoginEvent
}