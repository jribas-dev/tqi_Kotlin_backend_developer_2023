package br.com.tqi.backend_2023.service

import br.com.tqi.backend_2023.entity.Product

interface IProductService {
    fun save(product: Product): Product

    fun findAllByCategory(categoryId: Int?): List<Product>

    fun findProductById(productId: Long): Product
}