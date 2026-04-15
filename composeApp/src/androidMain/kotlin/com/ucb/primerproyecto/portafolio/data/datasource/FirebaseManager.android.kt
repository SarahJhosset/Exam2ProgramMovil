package com.ucb.primerproyecto.portafolio.data.datasource

import com.google.firebase.database.FirebaseDatabase

actual class FirebaseManager actual constructor() {

    private val database = FirebaseDatabase.getInstance().reference

    actual suspend fun saveData(path: String, value: String) {
        try {
            database.child(path).setValue(value)
            println("Firebase Android: Guardado exitoso en $path")
        } catch (e: Exception) {
            println("Firebase Android: Error - ${e.message}")
        }
    }
}
