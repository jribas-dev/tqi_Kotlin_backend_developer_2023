package br.com.tqi.backend_2023.service.impl

import br.com.tqi.backend_2023.entity.Category
import br.com.tqi.backend_2023.exception.BusinessException
import br.com.tqi.backend_2023.repository.CategoryRepository
import br.com.tqi.backend_2023.service.ICategoryService
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
): ICategoryService {

    override fun save(category: Category): Category {
        return this.categoryRepository.save(category)
    }

    override fun findByCategoryId(categoryId: Int): Category {
        return this.categoryRepository.findById(categoryId).orElseThrow{
            throw BusinessException("Category Id $categoryId not found")
        }
    }

    override fun findAllByParent(parentId: Int?): List<Category> {
        return this.categoryRepository.findAllByParentId(parentId)
    }

    override fun findAllByRoot(): List<Category> {
        return this.categoryRepository.findAllByRoot()
    }

    override fun parentCount(parentId: Int?): Int {
        return this.categoryRepository.parentCount(parentId)
    }

    override fun deleteByCategoryId(categoryId: Int): Map<String, String> {
        val category: Category = this.categoryRepository.findById(categoryId).orElseThrow {
            throw BusinessException("Category Id $categoryId not exists")
        }
        val parentCount: Int = this.categoryRepository.parentCount(categoryId)
        if (parentCount > 0) {
            return mutableMapOf("Category" to category.categoryName, "Message" to "Cannot be deleted, $parentCount parents found")
        }
        this.categoryRepository.deleteById(categoryId)
        return mutableMapOf("Category" to category.categoryName, "Message" to "Has been deleted")
    }

}