package br.com.tqi.backend_2023.dto.response

import br.com.tqi.backend_2023.entity.Product
import java.math.BigDecimal

data class ProductView(
    val productId: Long?,
    val productName: String,
    val unit: String,
    val unitPrice: BigDecimal,
    val categoryName: String?
) {
    constructor(product: Product) : this (
        productId = product.productId,
        productName = product.productName,
        unit = product.unit,
        unitPrice = product.unitPrice,
        categoryName = product.category?.categoryName
    )
}
