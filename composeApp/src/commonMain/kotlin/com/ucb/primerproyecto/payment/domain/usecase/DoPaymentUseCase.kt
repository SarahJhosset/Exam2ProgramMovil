package com.ucb.primerproyecto.payment.domain.usecase

import com.ucb.primerproyecto.payment.domain.model.CreditCard
import com.ucb.primerproyecto.payment.domain.repository.PaymentRepository
import com.ucb.primerproyecto.warehouse.domain.repository.WarehouseRepository


class DoPaymentUseCase(
    val paymentRepository: PaymentRepository,
    val warehouseRepository: WarehouseRepository
) {

}