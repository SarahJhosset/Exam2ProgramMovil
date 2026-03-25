package com.ucb.primerproyecto.buy_package.domain.usecase

import com.ucb.primerproyecto.buy_package.domain.model.DataPacketModel
import com.ucb.primerproyecto.buy_package.domain.repository.BuyPackageRepository

class GetPackageUseModel(
    val repository: BuyPackageRepository
) {
    suspend fun invoke():List<DataPacketModel>{
        return repository.getPackage()
    }
}