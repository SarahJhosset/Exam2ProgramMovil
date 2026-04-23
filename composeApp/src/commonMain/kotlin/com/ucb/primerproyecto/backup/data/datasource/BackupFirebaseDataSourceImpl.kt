package com.ucb.primerproyecto.backup.data.datasource

import com.google.firebase.database.FirebaseDatabase
import com.ucb.primerproyecto.dollar.domain.model.DollarModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BackupFirebaseDataSourceImpl : BackupFirebaseDataSource {

    private val db = FirebaseDatabase.getInstance().reference

    override suspend fun backupDollars(dollars: List<DollarModel>): Boolean {
        return suspendCancellableCoroutine { continuation ->

            val timestamp = System.currentTimeMillis()
            val backupRef = db.child("backups")
                .child("dollars")
                .child(timestamp.toString())

            val data = dollars.mapIndexed { index, dollar ->
                index.toString() to mapOf(
                    "id" to dollar.id,
                    "dollarOfficial" to dollar.dollarOfficial,
                    "dollarParallel" to dollar.dollarParallel,
                    "timestamp" to dollar.timestamp,
                    "backedUpAt" to timestamp
                )
            }.toMap()

            backupRef.setValue(data)
                .addOnSuccessListener {
                    continuation.resume(true)
                }
                .addOnFailureListener { error ->
                    continuation.resumeWithException(error)
                }
        }
    }
}