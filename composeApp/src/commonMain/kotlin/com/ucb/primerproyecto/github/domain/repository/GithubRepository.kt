package com.ucb.primerproyecto.github.domain.repository

import com.ucb.primerproyecto.github.domain.model.GithubModel

interface GithubRepository {
    suspend fun obtainAvatar(avatar:String): GithubModel
}