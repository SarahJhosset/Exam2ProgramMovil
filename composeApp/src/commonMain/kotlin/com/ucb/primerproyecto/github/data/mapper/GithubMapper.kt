package com.ucb.primerproyecto.github.data.mapper

import com.ucb.primerproyecto.github.data.dto.UserDto
import com.ucb.primerproyecto.github.domain.model.GithubModel

fun UserDto.toModel()= GithubModel(
    name = name ?: "",
    urlImage = avatarURL,
    avatar = login,
    bio=bio?:"",
    company = company?:""
)