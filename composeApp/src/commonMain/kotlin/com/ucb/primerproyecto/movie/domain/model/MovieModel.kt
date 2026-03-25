package com.ucb.primerproyecto.movie.domain.model

// Este data class representa UNA película dentro de tu app
data class MovieModel(
    // Descripción de la película (sin lógica, solo dato)
    val description: String,
    val title: String,
    val pathUrl: String,
    // Tiene valor por defecto = 0
    val rating:Int = 0
)
//este archivo es como un modelo puro , como una caja de datos
//solo guarda informacion