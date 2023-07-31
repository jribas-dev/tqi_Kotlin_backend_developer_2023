package br.com.tqi.backend_2023.service.impl

import br.com.tqi.backend_2023.entity.Cart
import br.com.tqi.backend_2023.exception.BusinessException
import br.com.tqi.backend_2023.repository.CartRepository
import br.com.tqi.backend_2023.service.ICartService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartService (
    private val cartRepository: CartRepository,
): ICartService{

    override fun new(): Cart {
        val cart = Cart()
        return this.cartRepository.save(cart)
    }

    override fun findByCartCode(cartCode: UUID): Cart? {
        return (this.cartRepository.findById(cartCode).orElseThrow() ?: throw BusinessException("Cart Code $cartCode not exist"))
    }

}