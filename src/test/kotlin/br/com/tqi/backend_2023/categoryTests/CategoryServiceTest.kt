package br.com.tqi.backend_2023.categoryTests

import br.com.tqi.backend_2023.entity.Category
import br.com.tqi.backend_2023.exception.BusinessException
import br.com.tqi.backend_2023.repository.CategoryRepository
import br.com.tqi.backend_2023.service.impl.CategoryService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class CategoryServiceTest {
    @MockK
    lateinit var categoryRepository: CategoryRepository
    @InjectMockKs
    lateinit var categoryService: CategoryService

    @Test
    fun `should create category`(){
        //given
        val fakeCategory: Category = buildCategory()
        every { categoryRepository.save(any()) } returns fakeCategory
        //when
        val actual: Category = categoryService.save(fakeCategory)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCategory)
        verify(exactly = 1) { categoryRepository.save(fakeCategory) }
    }

    @Test
    fun `should find category by id`() {
        //given
        val fakeId: Int = Random().nextInt()
        val fakeCategory: Category = buildCategory(categoryId = fakeId)
        every { categoryRepository.findById(fakeId) } returns Optional.of(fakeCategory)
        //when
        val actual: Category = categoryService.findByCategoryId(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Category::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCategory)
        verify(exactly = 1) { categoryRepository.findById(fakeId) }
    }

    @Test
    fun `should not find category by invalid id and throw BusinessException`() {
        //given
        val fakeId: Int = Random().nextInt()
        every { categoryRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { categoryService.findByCategoryId(fakeId) }
            .withMessage("Category Id $fakeId not found")
        verify(exactly = 1) { categoryRepository.findById(fakeId) }
    }

    @Test
    fun `should delete category by id`() {
        //given
        val fakeId: Int = Random().nextInt()
        val fakeCategory: Category = buildCategory(categoryId = fakeId)
        every { categoryRepository.findById(fakeId) } returns Optional.of(fakeCategory)
        every { categoryRepository.parentCount(fakeId) } returns 0
        every { categoryRepository.deleteById(fakeId) } just runs
        //when
        val ret: Map<String, String> = categoryService.deleteByCategoryId(fakeId)
        //then
        verify(exactly = 1) { categoryRepository.findById(fakeId) }
        verify(exactly = 1) { categoryRepository.parentCount(fakeId) }
        verify(exactly = 1) { categoryRepository.deleteById(fakeId) }
        Assertions.assertThat(ret).isNotNull
    }

    companion object {
        fun buildCategory(
            categoryId: Int = 1,
            categoryName: String = "BEBIDAS",
            categoryParent: Int? = null,
        ) = Category(
            categoryId = categoryId,
            categoryName = categoryName,
            categoryParent = categoryParent,
        )
    }
}