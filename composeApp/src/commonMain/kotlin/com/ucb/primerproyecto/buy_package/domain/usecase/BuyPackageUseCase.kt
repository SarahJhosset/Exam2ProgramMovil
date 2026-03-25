package com.ucb.primerproyecto.buy_package.domain.usecase

import com.ucb.primerproyecto.buy_package.domain.repository.BuyPackageRepository

class BuyPackageUseCase(
    val repository: BuyPackageRepository
) {
    suspend fun invoke(id:String): Boolean{
        return repository.buyPackage(id)
    }
}