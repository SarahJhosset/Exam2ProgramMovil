package com.ucb.primerproyecto.portafolio.data.mapper

import com.ucb.primerproyecto.portafolio.domain.model.PortafolioModel
import com.ucb.primerproyecto.portafolio.data.dto.PortafolioDto

fun PortafolioModel.toDto(): PortafolioDto {
    return PortafolioDto(
        totalBalance = totalBalance,
        percentIncrese = percentIncrese,
        depositFiat = depositFiat,
        timestamp = timestamp
    )
}