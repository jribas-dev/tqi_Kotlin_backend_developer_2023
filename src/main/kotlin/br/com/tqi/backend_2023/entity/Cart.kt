package br.com.tqi.backend_2023.entity

import br.com.tqi.backend_2023.enumm.CartStatus
import jakarta.persistence.*
import java.util.UUID

@Entity
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val cartCode: UUID? = null,

    @Enumerated
    var cartStatus: CartStatus = CartStatus.OPENED,
)
