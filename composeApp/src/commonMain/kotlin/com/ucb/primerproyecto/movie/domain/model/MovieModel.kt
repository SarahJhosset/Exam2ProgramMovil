package com.ucb.primerproyecto.movie.domain.model

data class MovieModel(
    val description: String,
    val title: String,
    val pathUrl: String,
    val rating:Int=0
)