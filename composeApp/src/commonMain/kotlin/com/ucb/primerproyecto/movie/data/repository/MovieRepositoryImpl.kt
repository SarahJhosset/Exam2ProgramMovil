package com.ucb.primerproyecto.movie.data.repository

import com.ucb.primerproyecto.movie.data.datasource.MovieRemoteDataSource
import com.ucb.primerproyecto.movie.data.mapper.toModel
import com.ucb.primerproyecto.movie.domain.model.MovieModel
import com.ucb.primerproyecto.movie.domain.repository.MovieRepository

class MovieRepositoryImpl (
    val remote: MovieRemoteDataSource
): MovieRepository{
    // override suspend fun getMovies(): MovieModel{
    //   val response=remote.getMovie()
    // return response.toModel()
    //}
    override suspend fun getMovies(): List<MovieModel> {
        return remote.getMovie().map{it.toModel()}
    }

}