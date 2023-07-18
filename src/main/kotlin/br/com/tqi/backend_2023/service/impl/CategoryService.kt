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

    override fun findAllByParent(parentId: Int?): Set<Category> {
        return this.categoryRepository.findAllByParentId(parentId)
    }

    override fun findAllByRoot(): Set<Category> {
        return this.categoryRepository.findAllByRoot()
    }

    override fun deleteByCategoryId(categoryId: Int): Map<String, String> {
        val category: Category = this.categoryRepository.findById(categoryId).orElseThrow {
            throw BusinessException("Category Id $categoryId not exists")
        }
        this.categoryRepository.deleteById(categoryId)
        return mutableMapOf("Category" to category.categoryName, "Message" to "Has been deleted")
    }

}