package br.com.tqi.backend_2023.dto.response

import br.com.tqi.backend_2023.entity.Sale
import br.com.tqi.backend_2023.enumm.CartStatus
import br.com.tqi.backend_2023.enumm.SaleStatus
import java.time.LocalDateTime
import java.util.*

data class SaleView(
    val saleId: Long?,
    val cartCode: UUID?,
    val cartStatus: CartStatus?,
    var saleStatus: SaleStatus,
    val saleDate: LocalDateTime,
    val totalSale: Double = 0.00,
    var totalPaid: Double = 0.00,
) {
    constructor(sale: Sale) : this (
        saleId = sale.saleId,
        cartCode = sale.cart?.cartCode,
        cartStatus = sale.cart?.cartStatus,
        saleStatus = sale.saleStatus,
        saleDate = sale.saleDate,
        totalSale = sale.totalSale,
        totalPaid = sale.totalPaid,
    )
}
