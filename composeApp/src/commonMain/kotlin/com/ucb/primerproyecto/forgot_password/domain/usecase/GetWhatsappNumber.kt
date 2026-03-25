package com.ucb.primerproyecto.forgot_password.domain.usecase

import com.ucb.primerproyecto.forgot_password.domain.repository.ForgotPasswordRepository

class GetWhatsappNumber(
    private val repository: ForgotPasswordRepository
) {
    suspend fun invoke(): String{
        return repository.getWhatsappNumber()
    }
}