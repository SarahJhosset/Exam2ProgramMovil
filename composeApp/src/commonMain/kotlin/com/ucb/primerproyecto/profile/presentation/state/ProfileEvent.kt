package com.ucb.primerproyecto.profile.presentation.state

sealed interface ProfileEvent {
    object OnClick: ProfileEvent
    //evento es el onclick
    //el efecto es navegar
}