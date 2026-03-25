package com.ucb.primerproyecto.movie.data.datasource

import com.ucb.primerproyecto.movie.data.dto.MovieDto
import com.ucb.primerproyecto.movie.data.dto.PageDto

interface MovieRemoteDataSource {
    suspend fun getMovie(): List<MovieDto>
}