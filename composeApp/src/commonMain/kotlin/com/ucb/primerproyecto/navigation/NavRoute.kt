package com.ucb.primerproyecto.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object Profile: NavRoute()

    @Serializable
    object ProfileEdit: NavRoute()

    @Serializable
    object Github: NavRoute()

    @Serializable
    object Movies: NavRoute()

    @Serializable
    data class MovieDetail(
        val title: String,
        val image: String,
        val description: String
    ): NavRoute()


    @Serializable
    object Dollar: NavRoute()

    @Serializable
    object Portafolio: NavRoute()
}
