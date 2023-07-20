package br.com.tqi.backend_2023.service

import br.com.tqi.backend_2023.entity.CartItems
import java.math.BigDecimal
import java.util.UUID

interface ICartItemsService {

    fun save(cartItem: CartItems): CartItems

    fun deleteByItemId(itemId: Long): Map<String, String>

    fun viewCartItems(cartCode: UUID): List<CartItems>

    fun viewCartValue(cartCode: UUID): BigDecimal

}