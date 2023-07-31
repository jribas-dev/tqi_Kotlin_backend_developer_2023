package br.com.tqi.backend_2023.repository

import br.com.tqi.backend_2023.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface SaleRepository : JpaRepository<Sale, Long> {

    @Query(value = "SELECT * FROM SALE WHERE CART_CODE = ?1", nativeQuery = true)
    fun findByCartCode(cartCode: UUID) : Sale?

}