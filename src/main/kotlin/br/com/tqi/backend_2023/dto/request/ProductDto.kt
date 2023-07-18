package br.com.tqi.backend_2023.dto.request

import br.com.tqi.backend_2023.entity.Category
import br.com.tqi.backend_2023.entity.Product
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal

data class ProductDto(
    @field:NotNull(message = "Product name must be informed") val productName: String,
    @field:Length(min = 2, max = 5, message = "Unit size must be between 2 and 5 characters") val unit: String,
    @field:NotNull(message = "Unit price must be informed") val unitPrice: BigDecimal,
    @field:NotNull(message = "Category Id must be informed") val categoryId: Int
) {
    fun toEntity(): Product = Product(
       productName = this.productName,
       unit = this.unit,
       unitPrice = this.unitPrice,
       category = Category(categoryId = this.categoryId)
    )
}
