package com.ucb.primerproyecto.dollar.data.mapper

import com.ucb.primerproyecto.dollar.data.entity.DollarEntity
import com.ucb.primerproyecto.dollar.domain.model.DollarModel

fun DollarModel.toEntity() = DollarEntity(
    dollarOfficial,
    dollarParallel
)

fun DollarEntity.toModel() =  DollarModel(
    id,
    dollarOfficial,
    dollarParallel
)