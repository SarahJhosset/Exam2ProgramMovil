package com.ucb.primerproyecto.profile.domain.usecase

import com.ucb.primerproyecto.profile.domain.model.ProfileModel
import com.ucb.primerproyecto.profile.domain.repository.ProfileRepository

class SaveProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend fun invoke(profile: ProfileModel) {
        repository.create(profile)
    }
}