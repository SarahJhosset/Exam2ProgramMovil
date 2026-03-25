package com.ucb.primerproyecto.movie.presentation.state

import com.ucb.primerproyecto.movie.domain.model.MovieModel

sealed interface MovieEffect {//efecto solo una vez
    data class ShowError(val message:String): MovieEffect
    data class NavigateToDetail(val movie: MovieModel): MovieEffect
}