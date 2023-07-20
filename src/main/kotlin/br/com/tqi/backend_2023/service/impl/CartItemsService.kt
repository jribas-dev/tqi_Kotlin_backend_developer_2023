package br.com.tqi.backend_2023.service.impl

import br.com.tqi.backend_2023.entity.CartItems
import br.com.tqi.backend_2023.exception.BusinessException
import br.com.tqi.backend_2023.repository.CartItemsRepository
import br.com.tqi.backend_2023.service.ICartItemsService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class CartItemsService(
    val cartItemsRepository: CartItemsRepository,
    val cartService: CartService,
    val productService: ProductService,
): ICartItemsService {

    override fun save(cartItem: CartItems): CartItems {
        cartItem.apply {
            cart = cartService.findByCartCode(cartItem.cart?.cartCode!!)
            product = productService.findProductById(cartItem.product?.productId!!)
        }
        return this.cartItemsRepository.save(cartItem)
    }

    override fun deleteByItemId(itemId: Long): Map<String, String> {
        val cartItem: CartItems = this.cartItemsRepository.findById(itemId).orElseThrow {
            throw BusinessException("Product ID $itemId not exists")
        }
        this.cartItemsRepository.deleteById(itemId)
        return mutableMapOf("Product" to (cartItem.product?.productName ?: ""), "Message" to "Has been deleted")
    }

    override fun viewCartItems(cartCode: UUID): List<CartItems> {
        return this.cartItemsRepository.findAllByCartCode(cartCode)
    }

    override fun viewCartValue(cartCode: UUID): BigDecimal {
        return this.cartItemsRepository.totalCart(cartCode)
    }

}