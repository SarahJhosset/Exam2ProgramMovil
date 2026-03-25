package com.ucb.primerproyecto.movie.data.service

import com.ucb.primerproyecto.movie.data.datasource.MovieRemoteDataSource
import com.ucb.primerproyecto.movie.data.dto.MovieDto
import com.ucb.primerproyecto.movie.data.dto.PageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

class MovieApiService: MovieRemoteDataSource {
    private val client=HttpClient{
        install(ContentNegotiation){
            json(
                Json{
                    prettyPrint=true
                    isLenient=true
                    ignoreUnknownKeys=true
                }
            )
        }
    }

    override suspend fun getMovie(): List<MovieDto> {
        val response = client.get("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fa3e844ce31744388e07fa47c7c5d8c3")
        try {
            val body = response.body<PageDto>()
            return body.results
        } catch (e: Exception) {
            throw e
        }
    }


}