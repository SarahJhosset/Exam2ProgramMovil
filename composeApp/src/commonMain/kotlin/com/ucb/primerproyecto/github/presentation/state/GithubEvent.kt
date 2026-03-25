package com.ucb.primerproyecto.github.presentation.state

sealed interface GithubEvent {
    object OnClickFind: GithubEvent
    data class OnChangeAvatar(val value: String): GithubEvent
}