package com.ucb.primerproyecto.github.domain.usecase

import com.ucb.primerproyecto.github.domain.model.GithubModel
import com.ucb.primerproyecto.github.domain.repository.GithubRepository

class GetAvatarUseCase (
    val repository: GithubRepository
){
    suspend fun invoke (model: GithubModel): GithubModel{
        return  repository.obtainAvatar(model.avatar)
    }
}