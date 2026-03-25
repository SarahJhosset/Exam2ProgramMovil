package com.ucb.primerproyecto.movie.domain.repository

import com.ucb.primerproyecto.movie.domain.model.MovieModel

interface MovieRepository {
    suspend fun getMovies(): List<MovieModel>

    //suspend fun obtainPicture(picture:String):String
}