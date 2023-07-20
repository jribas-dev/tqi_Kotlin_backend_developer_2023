package br.com.tqi.backend_2023.dto.request

import br.com.tqi.backend_2023.entity.Cart
import br.com.tqi.backend_2023.entity.CartItems
import br.com.tqi.backend_2023.entity.Product
import jakarta.validation.constraints.NotNull
import java.util.*

data class CartItemsDto(
    @field:NotNull(message = "Cart Code must be informed") val cartCode: UUID?,
    @field:NotNull(message = "Product Id must be informed") val productId: Long?,
    @field:NotNull(message = "Sale Qtd must be informed") val saleQtd: Double,
    @field:NotNull(message = "Sale price must be informed") val salePrice: Double,
) {
    fun toEntity(): CartItems = CartItems(
        cart = Cart(cartCode = this.cartCode),
        product = Product(productId = this.productId),
        saleQtd = this.saleQtd,
        salePrice = this.salePrice,
        totalItem = this.saleQtd * this.salePrice
    )
}

