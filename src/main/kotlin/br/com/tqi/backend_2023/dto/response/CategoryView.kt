package br.com.tqi.backend_2023.dto.response

import br.com.tqi.backend_2023.entity.Category

data class CategoryView(
    val categoryId: Int?,
    val categoryName: String,
    val categoryParent: Int?,
) {
    constructor(category: Category) : this (
        categoryId = category.categoryId,
        categoryName = category.categoryName,
        categoryParent = category.categoryParent
    )
}
