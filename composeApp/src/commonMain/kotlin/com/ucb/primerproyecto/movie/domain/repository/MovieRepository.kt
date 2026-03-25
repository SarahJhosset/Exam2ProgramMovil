package com.ucb.primerproyecto.movie.domain.repository

import com.ucb.primerproyecto.movie.domain.model.MovieModel

//esto es un CONTRATO, no implementacion
interface MovieRepository {
    //funcion suspend porque usa corrutinas(llamadas async) asincronas
    suspend fun getMovies(): List<MovieModel>

    //suspend fun obtainPicture(picture:String):String
}