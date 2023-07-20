package br.com.tqi.backend_2023.repository

import br.com.tqi.backend_2023.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository : JpaRepository<Cart, UUID>