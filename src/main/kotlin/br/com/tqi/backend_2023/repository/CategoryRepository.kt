package br.com.tqi.backend_2023.repository

import br.com.tqi.backend_2023.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, Int> {
    @Query(value = "SELECT * FROM CATEGORY WHERE CATEGORY_PARENT = ?1", nativeQuery = true)
    fun findAllByParentId(parentId: Int?) : List<Category>

    @Query(value = "SELECT COALESCE(COUNT(*), 0) AS PARENT_COUNT FROM CATEGORY WHERE CATEGORY_PARENT = ?1", nativeQuery = true)
    fun parentCount(parentId: Int?) : Int

    @Query(value = "SELECT * FROM CATEGORY WHERE CATEGORY_PARENT IS NULL", nativeQuery = true)
    fun findAllByRoot() : List<Category>
}