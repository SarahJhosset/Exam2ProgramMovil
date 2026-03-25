package com.ucb.primerproyecto.movie.presentation.state

import com.ucb.primerproyecto.movie.domain.model.MovieModel

data class MovieUiState(
    val isLoading: Boolean = false,
    val list: List<MovieModel> = emptyList()
)