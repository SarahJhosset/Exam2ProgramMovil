package com.ucb.primerproyecto.movie.domain.usecase

import com.ucb.primerproyecto.movie.domain.model.MovieModel
import com.ucb.primerproyecto.movie.domain.repository.MovieRepository


//caso de uso :obtener peliculas
class GetMoviesUseCase(
    //recibe el repositorio (inyeccion de dependencias)
    val repository: MovieRepository
) {
    //funcion suspend porque llama algo async
    suspend fun invoke(): List<MovieModel> {
        return repository.getMovies()//llama al repositorio
    }
}