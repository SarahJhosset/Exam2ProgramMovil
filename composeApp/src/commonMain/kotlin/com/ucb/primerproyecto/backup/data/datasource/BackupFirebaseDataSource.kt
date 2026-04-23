package com.ucb.primerproyecto.backup.data.datasource

import com.ucb.primerproyecto.dollar.domain.model.DollarModel

interface BackupFirebaseDataSource {
    suspend fun backupDollars(dollars: List<DollarModel>): Boolean
}