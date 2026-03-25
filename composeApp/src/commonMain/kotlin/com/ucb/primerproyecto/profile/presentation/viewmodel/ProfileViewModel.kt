package com.ucb.primerproyecto.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.primerproyecto.profile.presentation.state.ProfileEffect
import com.ucb.primerproyecto.profile.presentation.state.ProfileEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val _effect= MutableSharedFlow <ProfileEffect>()
    val effect =_effect.asSharedFlow()

    //events on event emite la funcion de los efectos
    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.OnClick ->
                emit(ProfileEffect.NavigateToEditProfile)
        }
    }
    //effects , con el mutable shared state
    private fun emit(effect: ProfileEffect){
        viewModelScope.launch{
            _effect.emit(effect)
        }
    }
}