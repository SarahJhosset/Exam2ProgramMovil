package com.ucb.primerproyecto.profile.domain.model

import proyectoucb.composeapp.generated.resources.Res

data class ProfileModel (
    val id:String,
    val name: String,
    val email: String,
    val cellphone:String,
    val description:String,
    val pathURL:String,

)