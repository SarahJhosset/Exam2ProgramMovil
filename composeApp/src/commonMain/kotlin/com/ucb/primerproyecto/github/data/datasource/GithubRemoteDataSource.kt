package com.ucb.primerproyecto.github.data.datasource

import com.ucb.primerproyecto.github.data.dto.UserDto

interface GithubRemoteDataSource {
    suspend fun getUser(nickname:String): UserDto
}