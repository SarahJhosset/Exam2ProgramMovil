package com.ucb.primerproyecto.profile.domain.usecase

import com.ucb.primerproyecto.movie.domain.repository.MovieRepository
import com.ucb.primerproyecto.profile.domain.model.ProfileModel
import com.ucb.primerproyecto.profile.domain.repository.ProfileRepository

class EditProfileUseCase(
    val repository: ProfileRepository
) {
    suspend fun invoke(model: ProfileModel){
        repository.update(profile=model)
    }
}