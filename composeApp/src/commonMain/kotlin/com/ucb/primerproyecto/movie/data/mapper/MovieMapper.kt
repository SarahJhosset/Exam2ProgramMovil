package com.ucb.primerproyecto.movie.data.mapper

import com.ucb.primerproyecto.movie.data.dto.MovieDto
import com.ucb.primerproyecto.movie.domain.model.MovieModel

fun MovieDto.toModel()= MovieModel(
    description = "",
    title = title,
    pathUrl ="https://image.tmdb.org/t/p/w185$picture" ,
)