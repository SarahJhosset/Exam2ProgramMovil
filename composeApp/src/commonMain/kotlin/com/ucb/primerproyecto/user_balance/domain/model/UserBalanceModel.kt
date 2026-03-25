package com.ucb.primerproyecto.user_balance.domain.model

data class UserBalanceModel(
    val balance:Double,
    val dataMB:Double,
    val minutes:String,
    val sms: Int,
)
