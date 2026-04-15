package com.ucb.primerproyecto.portafolio.domain.usecase

import com.ucb.primerproyecto.portafolio.domain.model.PortafolioModel
import com.ucb.primerproyecto.portafolio.domain.repository.PortafolioRespository

class SaveDataUseCase(
    private val repository: PortafolioRespository
) {
    suspend fun invoke(model: PortafolioModel){
        repository.save(model)
    }
}