package com.ucb.primerproyecto.profile.presentation.state

sealed interface ProfileEffect {
    object  NavigateToEditProfile: ProfileEffect
}