package com.ucb.primerproyecto.payment.domain.repository

import com.ucb.primerproyecto.payment.domain.model.CreditCard
import com.ucb.primerproyecto.profile.domain.model.ProfileModel


interface PaymentRepository {
    suspend fun executePayment(
        paymentModel: CreditCard,
        price: Double
    ): Boolean
}