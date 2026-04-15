package com.ucb.primerproyecto.portafolio.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.primerproyecto.firebase.getToken
import com.ucb.primerproyecto.portafolio.domain.model.PortafolioModel
import com.ucb.primerproyecto.portafolio.domain.usecase.SaveDataUseCase
import com.ucb.primerproyecto.portafolio.presentation.state.Holding
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEvent
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PortafolioViewModel(
    private val saveDataUseCase: SaveDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortafolioUiState())
    val uiState: StateFlow<PortafolioUiState> = _uiState

    init {
        loadData()
        obtenerToken()
    }

    fun onEvent(event: PortafolioEvent) {
        when (event) {
            is PortafolioEvent.OnRefresh -> loadData()

            is PortafolioEvent.OnCoinClick -> {
                println("Click en ${event.coin}")
            }

            is PortafolioEvent.OnAddClick -> {
                println("🔥 CLICK DETECTADO")
                saveSampleData()
            }
        }
    }

    private fun loadData() {
        _uiState.value = PortafolioUiState(
            balanceTotal = 32540.80,
            changePercentage = 4.2,
            stables = 8500.0,
            ganancias = 3210.50,
            holdings = listOf(
                Holding("BTC", 0.45, 31500.0),
                Holding("ETH", 2.1, 8400.0),
                Holding("SOL", 55.0, 7700.0),
                Holding("LINK", 150.0, 2400.0)
            )
        )
    }

    private fun saveSampleData() {

        val model = PortafolioModel(
            id = (1..1000).random(), // para probar varios registros
            totalBalance = 32540.80f,
            percentIncrese = 4.2f,
            depositFiat = 8500f,
            positiveBalance = 3210.5f,
            holdingModel = null,
            //timestamp = System.currentTimeMillis() // ✅ simple y sin librerías
        )

        viewModelScope.launch {
            println("🔥 ENVIANDO A FIREBASE...")
            saveDataUseCase.invoke(model)
        }
    }

    private fun obtenerToken() {
        viewModelScope.launch {
            try {
                val token = getToken()
                println("FCM_TOKEN: $token")
            } catch (e: Exception) {
                println("ERROR TOKEN: ${e.message}")
            }
        }
    }
}