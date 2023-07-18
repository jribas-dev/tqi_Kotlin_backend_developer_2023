package br.com.tqi.backend_2023.entity

import jakarta.persistence.*

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Int? = null,

    @Column(nullable = false)
    val categoryName: String = "",

    val categoryParent: Int? = null,
)
