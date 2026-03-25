package com.ucb.primerproyecto.product.domain.repository

import com.ucb.primerproyecto.product.domain.model.Product

interface ProductRepository {
    suspend fun findById(id: String): Product
}