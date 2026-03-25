package com.ucb.primerproyecto.payment.domain.model

data class CreditCard (
    val cardNumber: String,
    val experationDate: String,
    val cvv: String,
    val holder: String
)