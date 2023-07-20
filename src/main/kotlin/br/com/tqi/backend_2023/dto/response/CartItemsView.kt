package br.com.tqi.backend_2023.dto.response

import br.com.tqi.backend_2023.entity.CartItems
import java.util.*

data class CartItemsView(
    val cartCode: UUID?,
    val itemId: Long?,
    val productId: Long?,
    val productName: String?,
    val saleQtd: Double,
    val salePrice: Double,
    val saleTotal: Double,
) {
    constructor(cartItem: CartItems) : this(
        cartCode = cartItem.cart?.cartCode,
        itemId = cartItem.itemId,
        productId = cartItem.product?.productId,
        productName = cartItem.product?.productName,
        saleQtd = cartItem.saleQtd,
        salePrice = cartItem.salePrice,
        saleTotal = cartItem.totalItem,
    )
}
