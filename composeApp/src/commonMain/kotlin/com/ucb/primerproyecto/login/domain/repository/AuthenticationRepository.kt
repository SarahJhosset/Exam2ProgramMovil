package com.ucb.primerproyecto.login.domain.repository

import com.ucb.primerproyecto.login.domain.model.LoginModel

interface AuthenticationRepository {
    suspend fun login (model: LoginModel)
}