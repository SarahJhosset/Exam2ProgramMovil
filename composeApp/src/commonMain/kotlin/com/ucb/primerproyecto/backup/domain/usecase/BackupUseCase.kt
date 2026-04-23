package com.ucb.primerproyecto.backup.domain.usecase

import com.ucb.primerproyecto.dollar.domain.model.DollarModel
import com.ucb.primerproyecto.dollar.domain.repository.DollarRepository
import com.ucb.primerproyecto.deposit.domain.model.DepositModel

class BackupUseCase(
    private val dollarRepository: DollarRepository
) {
    data class BackupResult(
        val dollarsCount: Int,
        val success: Boolean,
        val message: String
    )

    suspend fun invoke(): BackupResult {
        return try {
            val dollars = dollarRepository.getList()

            BackupResult(
                dollarsCount = dollars.size,
                success = true,
                message = "Backup exitoso: ${dollars.size} registros de dólares"
            )
        } catch (e: Exception) {
            BackupResult(
                dollarsCount = 0,
                success = false,
                message = "Error en backup: ${e.message}"
            )
        }
    }
}