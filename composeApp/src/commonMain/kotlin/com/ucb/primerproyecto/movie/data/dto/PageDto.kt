package com.ucb.primerproyecto.movie.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PageDto(
    val results:List<MovieDto>,
)
