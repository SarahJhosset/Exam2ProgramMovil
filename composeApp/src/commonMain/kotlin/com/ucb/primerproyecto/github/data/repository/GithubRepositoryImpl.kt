package com.ucb.primerproyecto.github.data.repository

import com.ucb.primerproyecto.github.data.datasource.GithubRemoteDataSource
import com.ucb.primerproyecto.github.data.mapper.toModel
import com.ucb.primerproyecto.github.domain.model.GithubModel
import com.ucb.primerproyecto.github.domain.repository.GithubRepository

class GithubRepositoryImpl (
    val remote: GithubRemoteDataSource
): GithubRepository{
    override suspend fun obtainAvatar(avatar: String): GithubModel {
        val response=remote.getUser(avatar)
        return response.toModel()
    }
}