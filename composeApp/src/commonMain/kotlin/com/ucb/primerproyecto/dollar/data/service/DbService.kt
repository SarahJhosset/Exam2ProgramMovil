package com.ucb.primerproyecto.dollar.data.service

import com.ucb.primerproyecto.dollar.data.dao.DollarDao
import com.ucb.primerproyecto.dollar.data.datasource.DollarLocalDataSource
import com.ucb.primerproyecto.dollar.data.entity.DollarEntity

class DbService(val dollarDao: DollarDao) : DollarLocalDataSource {
    override suspend fun save(entity: DollarEntity) = dollarDao.insert(entity)
    override suspend fun list(): List<DollarEntity> = dollarDao.getList()
    override suspend fun count(): Int = dollarDao.count()
    override suspend fun deleteOldest(n: Int) = dollarDao.deleteOldest(n)
}