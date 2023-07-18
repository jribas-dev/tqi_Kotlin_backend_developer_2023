package br.com.tqi.backend_2023.controller

import br.com.tqi.backend_2023.dto.request.CategoryDto
import br.com.tqi.backend_2023.dto.response.CategoryView
import br.com.tqi.backend_2023.entity.Category
import br.com.tqi.backend_2023.service.impl.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/category")
class CategoryResource (private val categoryService: CategoryService) {

    @PostMapping
    fun saveCategory(
        @RequestBody
        @Valid categoryDto: CategoryDto
    ) : ResponseEntity<CategoryView> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            CategoryView(this.categoryService.save(categoryDto.toEntity()))
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<CategoryView> {
        val category: Category = this.categoryService.findByCategoryId(id)
        return ResponseEntity.status(HttpStatus.OK).body(CategoryView(category))
    }

    @GetMapping("/list")
    fun listByRoot(): ResponseEntity<List<CategoryView>> {
        val categoryList: MutableList<CategoryView>? = this.categoryService.findAllByRoot()
            .stream()
            .map { category: Category -> CategoryView(category) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(categoryList)
    }

    @GetMapping("/list/{id}")
    fun listByParent(@PathVariable(required = false) id: Int?): ResponseEntity<List<CategoryView>> {
        val categoryList: MutableList<CategoryView>? = this.categoryService.findAllByParent(id)
            .stream()
            .map { category: Category -> CategoryView(category) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(categoryList)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.deleteByCategoryId(id))
    }

}