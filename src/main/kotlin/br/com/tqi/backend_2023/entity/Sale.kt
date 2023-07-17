package br.com.tqi.backend_2023.entity

import br.com.tqi.backend_2023.enumm.SaleStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val saleId: Long? = null,

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartCode")
    val cart: Cart? = null,

    @Enumerated
    val saleStatus: SaleStatus = SaleStatus.IN_PROGRESS,

    @Column(nullable = false)
    val saleDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val totalSale: Double = 0.00,

    @Column(nullable = false)
    val totalPaid: Double = 0.00,
)
