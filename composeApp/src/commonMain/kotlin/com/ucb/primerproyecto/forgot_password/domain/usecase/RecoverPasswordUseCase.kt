package com.ucb.primerproyecto.forgot_password.domain.usecase

import com.ucb.primerproyecto.forgot_password.domain.repository.ForgotPasswordRepository

class RecoverPasswordUseCase(
    private val repository: ForgotPasswordRepository
) {
    suspend fun invoke(email:String):Boolean {
        return repository.recoverPassword(email)
    }
}