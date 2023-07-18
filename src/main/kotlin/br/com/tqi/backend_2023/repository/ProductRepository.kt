package br.com.tqi.backend_2023.repository;

import br.com.tqi.backend_2023.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM PRODUCT WHERE CATEGORY_ID = ?1", nativeQuery = true)
    fun findAllByCategoryId(categoryId: Int?) : List<Product>

}