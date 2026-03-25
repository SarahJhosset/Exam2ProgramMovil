package com.ucb.primerproyecto.profile.domain.repository

import com.ucb.primerproyecto.profile.domain.model.ProfileModel

interface ProfileRepository {
    suspend fun update(profile: ProfileModel)
    suspend fun  create(profile: ProfileModel)
    suspend fun findById(id: String): ProfileModel
}

/// metodos CRUD create,read q seria find by id ,update,delete