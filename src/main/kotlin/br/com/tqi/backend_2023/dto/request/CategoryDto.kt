package br.com.tqi.backend_2023.dto.request

import br.com.tqi.backend_2023.entity.Category
import jakarta.validation.constraints.NotEmpty

data class CategoryDto(
    @field:NotEmpty(message = "Category Name must be informed")
    val categoryName: String,
    val categoryParent: Int?
) {
    fun toEntity(): Category = Category (
        categoryName = this.categoryName,
        categoryParent = this.categoryParent
    )
}
