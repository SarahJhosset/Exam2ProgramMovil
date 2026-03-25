package com.ucb.primerproyecto.buy_package.domain.repository

import com.ucb.primerproyecto.buy_package.domain.model.DataPacketModel

interface BuyPackageRepository {
    suspend fun buyPackage(id: String): Boolean
    suspend fun getPackage():List<DataPacketModel>
}