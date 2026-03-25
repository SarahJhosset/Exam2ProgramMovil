package com.ucb.primerproyecto.movie.presentation.state

import com.ucb.primerproyecto.movie.domain.model.MovieModel

sealed interface MovieEvent {//acciones del usuario
    object  OnLoadMovies: MovieEvent
    data class OnRateMovie(val index:Int,val rating:Int): MovieEvent
    data class OnClickMovie(val movie: MovieModel): MovieEvent
}