package com.ucb.primerproyecto.github.presentation.state

import com.ucb.primerproyecto.github.domain.model.GithubModel

data class GithubUiState(
    val isLoading: Boolean = false,
    val nickname: String = "",
    val model: GithubModel = GithubModel()
)