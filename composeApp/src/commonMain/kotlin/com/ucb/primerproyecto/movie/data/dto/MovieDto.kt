package com.ucb.primerproyecto.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("poster_path")
    val picture:String,
    val title:String,
    @SerialName("overview")
    val description:String,
    val rating: Int = 0
)
