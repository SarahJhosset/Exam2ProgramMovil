package com.ucb.primerproyecto.portafolio.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PortafolioModel(
    val id: Int? = null,
    val totalBalance: Float,
    val percentIncrese: Float,
    val depositFiat: Float ,
    val positiveBalance: Float? = null,

    val holdingModel: HoldingModel?=null,
    var timestamp: Long = 0
)
