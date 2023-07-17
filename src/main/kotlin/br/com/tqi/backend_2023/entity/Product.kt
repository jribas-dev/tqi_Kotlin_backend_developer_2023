package br.com.tqi.backend_2023.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null,

    @Column(nullable = false)
    val productName: String = "",

    @Column(nullable = false)
    val unit: String = "UN",

    @Column(nullable = false)
    val unitPrice: BigDecimal = BigDecimal.ZERO,

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE]
    )
    @JoinColumn(name = "categoryId")
    val category: Category? = null,
)
