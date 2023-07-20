package br.com.tqi.backend_2023.repository

import br.com.tqi.backend_2023.entity.CartItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.UUID

@Repository
interface CartItemsRepository : JpaRepository<CartItems, Long> {

    @Query(value = "SELECT * FROM CART_ITEMS WHERE CART_CODE = ?1", nativeQuery = true)
    fun findAllByCartCode(cartCode: UUID) : List<CartItems>

    @Query(value = "SELECT SUM(SALE_PRICE * SALE_QTD) AS TOTAL FROM CART_ITEMS WHERE CART_CODE = ?1", nativeQuery = true)
    fun totalCart(cartCode: UUID) : BigDecimal

}