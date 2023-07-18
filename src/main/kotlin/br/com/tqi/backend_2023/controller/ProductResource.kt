package br.com.tqi.backend_2023.controller

import br.com.tqi.backend_2023.dto.request.ProductDto
import br.com.tqi.backend_2023.dto.response.ProductView
import br.com.tqi.backend_2023.entity.Product
import br.com.tqi.backend_2023.service.impl.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/product")
class ProductResource (private val productService: ProductService) {

    @PostMapping
    fun saveCategory(
        @RequestBody
        @Valid productDto: ProductDto
    ) : ResponseEntity<ProductView> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ProductView(this.productService.save(productDto.toEntity())
        ))
    }

    @GetMapping("/list", "/list/{id}")
    fun listByCategory(@PathVariable(required = false) id: Int?): ResponseEntity<List<ProductView>> {
        val productList: MutableList<ProductView>? = this.productService.findAllByCategory(id)
            .stream()
            .map { product: Product -> ProductView(product) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(productList)
    }

}