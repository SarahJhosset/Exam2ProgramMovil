package com.ucb.primerproyecto.payment.domain.usecase

import com.ucb.primerproyecto.payment.domain.model.CreditCard
import com.ucb.primerproyecto.payment.domain.repository.PaymentRepository
import com.ucb.primerproyecto.product.domain.model.Product
import com.ucb.primerproyecto.product.domain.repository.ProductRepository
import com.ucb.primerproyecto.warehouse.domain.repository.WarehouseRepository


class DoPaymentUseCase(
    val paymentRepository: PaymentRepository,
    val productRepository: ProductRepository,
    val warehouseRepository: WarehouseRepository
) {
    suspend fun invoke(
        productModel: Product,
        creditCard: CreditCard
    ) {
        val product = productRepository.findById(productModel.id)
        if(warehouseRepository.verifyStock(product.id)) {
            val resultPayment = paymentRepository.executePayment(creditCard, product.price)
            if(resultPayment) {
                warehouseRepository.reduceProduct(product.id)
            }
        }
    }
}