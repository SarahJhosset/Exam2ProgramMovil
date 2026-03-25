package com.ucb.primerproyecto.login.domain.usecase

import com.ucb.primerproyecto.login.domain.model.LoginModel
import com.ucb.primerproyecto.login.domain.repository.AuthenticationRepository
import proyectoucb.composeapp.generated.resources.Res

class DoLoginUseCase(
    val repository: AuthenticationRepository
) {
    suspend fun invoke(model: LoginModel){
        repository.login(model)
    }

}