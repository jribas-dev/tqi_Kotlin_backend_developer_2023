package br.com.tqi.backend_2023.service

import br.com.tqi.backend_2023.entity.Sale
import java.util.*

interface ISaleService {

    fun checkOut(cartCode: UUID): Sale

    fun payment(cartCode: UUID, totalPaid: Double): Sale

}