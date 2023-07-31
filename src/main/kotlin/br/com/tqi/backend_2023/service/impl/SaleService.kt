package br.com.tqi.backend_2023.service.impl

import br.com.tqi.backend_2023.entity.Cart
import br.com.tqi.backend_2023.entity.Sale
import br.com.tqi.backend_2023.enumm.CartStatus
import br.com.tqi.backend_2023.enumm.SaleStatus
import br.com.tqi.backend_2023.exception.BusinessException
import br.com.tqi.backend_2023.repository.CartRepository
import br.com.tqi.backend_2023.repository.SaleRepository
import br.com.tqi.backend_2023.service.ISaleService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SaleService (
    private val saleRepository: SaleRepository,
    private val cartRepository: CartRepository,
    private val cartService: CartService,
    private val cartItemsService: CartItemsService,
): ISaleService {

    override fun checkOut(cartCode: UUID): Sale {
        val cart: Cart = this.validCart(cartCode)
        val sale: Sale = this.saleRepository.findByCartCode(cartCode) ?: Sale(
            cart = cart, totalSale = this.cartItemsService.viewCartValue(cartCode).toDouble()
        )
        return this.saleRepository.save(sale)
    }

    override fun payment(cartCode: UUID, totalPaid: Double): Sale {
        val cart: Cart = this.validCart(cartCode)
        val sale: Sale = this.saleRepository.findByCartCode(cartCode) ?: throw BusinessException("Sale not IN_PROGRESS")
        return if (validPayment(sale.totalSale, totalPaid)) {
            cart.cartStatus = CartStatus.CLOSED
            this.cartRepository.save(cart)

            sale.totalPaid = totalPaid
            sale.saleStatus = SaleStatus.APPROVED
            this.saleRepository.save(sale)
        } else {
            sale.saleStatus = SaleStatus.REJECT
            this.saleRepository.save(sale)
        }
    }

    private fun validCart(cartCode: UUID): Cart {
        val cart: Cart = (this.cartService.findByCartCode(cartCode)) ?: throw BusinessException("Cart does not exist!")
        return if (cart.cartStatus == CartStatus.OPENED) cart
        else throw BusinessException("Cart already is closed")
    }

    private fun validPayment(totalSale: Double, totalPaid: Double): Boolean {
        return (totalPaid >= totalSale)
    }

}