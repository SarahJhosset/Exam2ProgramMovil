package com.ucb.primerproyecto.portafolio.domain.usecase

import com.ucb.primerproyecto.portafolio.domain.repository.RemoteConfigRepository

class CheckDepositEnabledUseCase(
    private val repo: RemoteConfigRepository
) {
    operator fun invoke(): Boolean {
        return repo.isDepositEnabled()
    }
}