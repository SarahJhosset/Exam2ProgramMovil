package com.ucb.primerproyecto.portafolio.data.repository

import com.ucb.primerproyecto.portafolio.data.datasource.FirebaseManager
import com.ucb.primerproyecto.portafolio.domain.model.PortafolioModel
import com.ucb.primerproyecto.portafolio.domain.repository.PortafolioRespository
import com.ucb.primerproyecto.portafolio.data.mapper.toDto
import kotlinx.serialization.json.Json

class PortafolioRepositoryImpl(
    private val firebase: FirebaseManager
) : PortafolioRespository {

    override suspend fun save(model: PortafolioModel) {
        val dto = model.toDto()
        val json = Json.encodeToString(dto)
        firebase.saveData("portafolio/${model.id}", json)
    }
}