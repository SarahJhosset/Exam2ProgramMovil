package com.ucb.primerproyecto.increment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class IncrementViewModel : ViewModel() {

    //parte 1 variable mutable
    //es la variable que cambia
    private val _counterState= MutableStateFlow(value=0)

    //parte 2 variable observable
    //observara los cambios de la variable mutable
    val counterState=_counterState.asStateFlow()

    //parte 3 evento acciones q un sistema o usuario ejecutan
    //evento desencadenador
    fun incrementData(){
        _counterState.value=_counterState.value+ 1
    }
}