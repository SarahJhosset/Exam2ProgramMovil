package com.ucb.primerproyecto.translation

object TranslationRepository {

    val english = mapOf(
        "notif_deposit_title"   to "Deposit Confirmed",
        "notif_deposit_body"    to "Your deposit has been processed successfully",
        "notif_backup_success"  to "Backup completed successfully",
        "notif_backup_failed"   to "Backup failed, retrying...",
        "notif_maintenance"     to "The app is under maintenance",
        "deposit_title"         to "Add Funds",
        "portfolio_title"       to "My Crypto Portfolio",
        "portfolio_balance"     to "Total Balance",
        "portfolio_history"     to "Deposit History",
        "portfolio_maintenance" to "App under maintenance"
    )

    val spanish = mapOf(
        "notif_deposit_title"   to "Depósito Confirmado",
        "notif_deposit_body"    to "Tu depósito fue procesado exitosamente",
        "notif_backup_success"  to "Respaldo completado exitosamente",
        "notif_backup_failed"   to "Respaldo fallido, reintentando...",
        "notif_maintenance"     to "La aplicación está en mantenimiento",
        "deposit_title"         to "Agregar fondos",
        "portfolio_title"       to "Mi portafolio Cripto",
        "portfolio_balance"     to "Balance Total",
        "portfolio_history"     to "Historial de depósitos",
        "portfolio_maintenance" to "Aplicación en mantenimiento"
    )

    fun getAll(locale: String): Map<String, String> {
        return if (locale.startsWith("es")) spanish else english
    }
}