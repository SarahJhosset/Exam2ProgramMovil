package com.ucb.primerproyecto.movie.domain.usecase
class FetchPopularMoviesUseCase {

    suspend operator fun invoke(): Result<List<String>> {
        return Result.success(listOf("Movie1", "Movie2"))
    }
}