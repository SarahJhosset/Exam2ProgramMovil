package com.ucb.primerproyecto.portafolio.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.primerproyecto.portafolio.domain.usecase.GetPortafolioUseCase
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEffect
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEvent
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PortafolioViewModel(
    private val getPortfolioUseCase: GetPortafolioUseCase
) : ViewModel() {

    var state by mutableStateOf(PortafolioUiState())
        private set

    // 🔥 EFFECTS (eventos de una sola vez)
    private val _effect = MutableSharedFlow<PortafolioEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadPortfolio()
    }

    // 📊 CARGAR DATOS DESDE FIREBASE
    private fun loadPortfolio() {
        getPortfolioUseCase { list ->

            val total = list.sumOf { it.amount }

            state = state.copy(
                deposits = list,
                totalBalance = total,
                isLoading = false
            )
        }
    }

    // 🎯 MANEJO DE EVENTOS
    fun onEvent(event: PortafolioEvent) {
        when (event) {

            PortafolioEvent.OnAddClick -> {
                navigateToDeposit()
            }
        }
    }

    // 🚀 NAVEGACIÓN
    private fun navigateToDeposit() {
        viewModelScope.launch {
            _effect.emit(PortafolioEffect.NavigateToDeposit)
        }
    }
}


//bloquear la aplicacion , remote config
//bloquear en los sistemas a los clientes qq haccen operaciones cuando no se debe ,
//el remote config esta diseñado para eso, no solo sirve para android ,  bloquear solo targeta de credito, , o solo puede ser para android
//leer de la base de datos