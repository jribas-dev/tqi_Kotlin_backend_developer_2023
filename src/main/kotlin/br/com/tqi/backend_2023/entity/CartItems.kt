package br.com.tqi.backend_2023.entity

import jakarta.persistence.*
import org.hibernate.annotations.Formula

@Entity
data class CartItems(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val itemId: Long? = null,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartCode")
    var cart: Cart? = null,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    var product: Product? = null,

    @Column(nullable = false)
    val salePrice: Double = 0.00,

    @Column(nullable = false)
    val saleQtd: Double = 0.00,

    @Formula("salePrice * saleQtd")
    val totalItem: Double,
)
