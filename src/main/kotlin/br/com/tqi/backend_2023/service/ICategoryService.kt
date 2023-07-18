package br.com.tqi.backend_2023.service

import br.com.tqi.backend_2023.entity.Category

interface ICategoryService {
    fun save(category: Category): Category
    fun findByCategoryId(categoryId: Int): Category
    fun findAllByParent(parentId: Int?): List<Category>
    fun findAllByRoot(): List<Category>
    fun parentCount(parentId: Int?) : Int
    fun deleteByCategoryId(categoryId: Int): Map<String, String>
}