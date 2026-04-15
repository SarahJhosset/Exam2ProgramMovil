package com.ucb.primerproyecto.portafolio.domain.repository

import com.ucb.primerproyecto.portafolio.domain.model.PortafolioModel

interface PortafolioRespository {
    suspend fun save(model: PortafolioModel)
}