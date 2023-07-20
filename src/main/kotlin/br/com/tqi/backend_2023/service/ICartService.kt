package br.com.tqi.backend_2023.service

import br.com.tqi.backend_2023.entity.Cart
import java.util.UUID

interface ICartService {

    fun new(): Cart

    fun findByCartCode(cartCode: UUID): Cart

}