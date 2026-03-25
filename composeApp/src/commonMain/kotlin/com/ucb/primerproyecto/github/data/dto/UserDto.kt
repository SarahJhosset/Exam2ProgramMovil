package com.ucb.primerproyecto.github.data.dto

import kotlinx.serialization.SerialName

data class UserDto(
    val login:String,
    @SerialName("avatar_url")
    val avatarURL: String,
    val name:String?=null,
    @SerialName("created_at")
    val createAt:String?=null,
    val bio:String?=null,
    val company:String?=null
)
