package br.com.tqi.backend_2023.service.impl

import br.com.tqi.backend_2023.entity.Product
import br.com.tqi.backend_2023.exception.BusinessException
import br.com.tqi.backend_2023.repository.ProductRepository
import br.com.tqi.backend_2023.service.IProductService
import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService,
): IProductService {

    override fun save(product: Product): Product {
        product.apply {
            category = categoryService.findByCategoryId(product.category?.categoryId!!)
        }
        return this.productRepository.save(product)
    }

    override fun findAllByCategory(categoryId: Int?): List<Product> {
        return if (categoryId == null) {
            this.productRepository.findAll()
        } else {
            this.productRepository.findAllByCategoryId(categoryId)
        }
    }

    override fun findProductById(productId: Long): Product {
        return (this.productRepository.findById(productId).orElseThrow() ?: throw BusinessException("Product ID $productId not exist"))
    }

}