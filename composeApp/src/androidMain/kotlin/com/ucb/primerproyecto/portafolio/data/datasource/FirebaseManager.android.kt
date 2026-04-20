package com.ucb.primerproyecto.portafolio.data.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ucb.primerproyecto.deposit.domain.model.DepositModel


actual class FirebaseManager actual constructor() {

    private val db = FirebaseDatabase.getInstance().reference

    actual fun observeDeposits(onResult: (List<DepositModel>) -> Unit) {

        db.child("deposits")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<DepositModel>()

                    for (child in snapshot.children) {

                        val amount = child.child("amount")
                            .getValue(Double::class.java) ?: 0.0

                        val currency = child.child("currency")
                            .getValue(String::class.java) ?: ""

                        val timestamp = child.child("timestamp")
                            .getValue(Long::class.java) ?: 0L

                        list.add(
                            DepositModel(
                                amount = amount,
                                currency = currency,
                                timestamp = timestamp
                            )
                        )
                    }

                    onResult(list)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}
