package com.ucb.primerproyecto.deposit.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.primerproyecto.deposit.domain.model.DepositModel
import com.ucb.primerproyecto.deposit.domain.usecase.SaveDepositUseCase
import com.ucb.primerproyecto.deposit.presentation.state.DepositEffect
import com.ucb.primerproyecto.deposit.presentation.state.DepositEvent
import com.ucb.primerproyecto.deposit.presentation.state.DepositUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Clock

class DepositViewModel(
    private val saveDepositUseCase: SaveDepositUseCase,
) : ViewModel() {

    // 🟢 STATE
    var state by mutableStateOf(DepositUiState())
        private set

    // 🔵 EFFECT (eventos de una sola vez)
    private val _effect = MutableSharedFlow<DepositEffect>()
    val effect = _effect.asSharedFlow()

    // 🎯 EVENT HANDLER
    fun onEvent(event: DepositEvent) {
        when (event) {

            is DepositEvent.OnAmountChange -> {
                state = state.copy(amount = event.value)
            }

            DepositEvent.OnSaveDeposit -> {
                saveDeposit()
            }
        }
    }

    // 💰 GUARDAR EN FIREBASE
    private fun saveDeposit() {
        viewModelScope.launch {
            val amountDouble = state.amount.toDoubleOrNull()

            if (amountDouble == null || amountDouble <= 0) {
                _effect.emit(DepositEffect.ShowError("Monto inválido"))
                return@launch

            }

            try {
                state = state.copy(isLoading = true)

                val deposit = DepositModel(
                    amount = amountDouble,
                    currency = state.currency,
                    timestamp = Clock.System.now().toEpochMilliseconds()
                )

                saveDepositUseCase(deposit)

                state = state.copy(isLoading = false)

                _effect.emit(DepositEffect.ShowSuccess("Depósito guardado"))

                _effect.emit(DepositEffect.NavigateToHome)

            } catch (e: Exception) {
                state = state.copy(isLoading = false)
                emitError("Error al guardar depósito")
            }
        }
    }

    private fun emitError(message: String) {
        viewModelScope.launch {
            _effect.emit(DepositEffect.ShowError(message))
        }
    }
}