package br.com.tqi.backend_2023.entity

import jakarta.persistence.*

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Int? = null,

    @Column(nullable = false)
    val categoryName: String = "",

    @ManyToOne()
    @JoinColumn(name = "parentId")
    val categoryParent: Category? = null,

    @OneToMany(mappedBy = "categoryParent")
    val subCategories: Set<Category> = mutableSetOf(),
)
