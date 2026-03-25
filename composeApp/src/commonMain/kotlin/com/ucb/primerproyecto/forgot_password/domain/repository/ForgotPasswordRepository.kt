package com.ucb.primerproyecto.forgot_password.domain.repository

interface ForgotPasswordRepository {
    suspend fun recoverPassword(email:String): Boolean
    suspend fun getWhatsappNumber(): String
}