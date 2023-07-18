package br.com.tqi.backend_2023.repository

import br.com.tqi.backend_2023.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, Int> {
    @Query(value = "SELECT * FROM CATEGORY WHERE PARENT_ID = ?1", nativeQuery = true)
    fun findAllByParentId(parentId: Int?) : Set<Category>

    @Query(value = "SELECT * FROM CATEGORY WHERE PARENT_ID IS NULL", nativeQuery = true)
    fun findAllByRoot() : Set<Category>
}