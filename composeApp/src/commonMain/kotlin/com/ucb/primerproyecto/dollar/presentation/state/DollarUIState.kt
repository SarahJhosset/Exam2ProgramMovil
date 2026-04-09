package com.ucb.primerproyecto.dollar.presentation.state

import com.ucb.primerproyecto.dollar.domain.model.DollarModel

data class DollarUIState(
    val list: List<DollarModel> = emptyList(),
    val isLoading: Boolean = true
)
