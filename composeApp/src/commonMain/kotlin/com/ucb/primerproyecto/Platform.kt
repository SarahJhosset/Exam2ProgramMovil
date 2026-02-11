package com.ucb.primerproyecto

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform