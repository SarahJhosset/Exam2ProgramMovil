package com.ucb.primerproyecto.movie.domain.usecase

import com.ucb.primerproyecto.movie.domain.model.MovieModel
import com.ucb.primerproyecto.movie.domain.repository.MovieRepository


class GetMoviesUseCase(
    val repository: MovieRepository
) {
    suspend fun invoke(): List<MovieModel> {
        return repository.getMovies()
    }
}