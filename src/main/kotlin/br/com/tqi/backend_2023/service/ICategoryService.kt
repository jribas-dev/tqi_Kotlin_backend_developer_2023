package br.com.tqi.backend_2023.service

import br.com.tqi.backend_2023.entity.Category

interface ICategoryService {
    fun save(category: Category): Category
    fun findByCategoryId(categoryId: Int): Category
    fun findAllByParent(parentId: Int?): Set<Category>
    fun findAllByRoot(): Set<Category>
    fun deleteByCategoryId(categoryId: Int): Map<String, String>
}