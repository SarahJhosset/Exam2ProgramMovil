package com.ucb.primerproyecto.deposit.data.mapper

import com.ucb.primerproyecto.deposit.data.dto.DepositDto
import com.ucb.primerproyecto.deposit.domain.model.DepositModel

fun DepositModel.toDto() = DepositDto(
    amount = amount,
    currency = currency,
    timestamp = timestamp
)