package com.ucb.primerproyecto.portafolio.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HoldingModel(
    val name:String,
    val icon: String,
    val valueTotal:Float,
    val value:String,
    val img:String
)
