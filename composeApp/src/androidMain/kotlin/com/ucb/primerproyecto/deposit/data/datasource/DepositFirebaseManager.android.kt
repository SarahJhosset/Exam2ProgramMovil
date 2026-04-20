package com.ucb.primerproyecto.deposit.data.datasource

import com.google.firebase.database.FirebaseDatabase
import com.ucb.primerproyecto.deposit.data.dto.DepositDto

actual class DepositFirebaseManager actual constructor() {

    private val db = FirebaseDatabase.getInstance().reference

    actual suspend fun saveDeposit(deposit: DepositDto) {
        val id = db.child("deposits").push().key ?: return
        db.child("deposits").child(id).setValue(deposit)
    }
}