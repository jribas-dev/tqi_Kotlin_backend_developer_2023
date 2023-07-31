package br.com.tqi.backend_2023.controller

import br.com.tqi.backend_2023.dto.response.SaleView
import br.com.tqi.backend_2023.entity.Sale
import br.com.tqi.backend_2023.service.impl.SaleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
class SaleResource (
    private val saleService: SaleService
) {

    @PostMapping("/checkout")
    fun saleCheckout(@RequestParam(value = "cartCode") cartCode: UUID) : ResponseEntity<SaleView> {
        val sale: Sale = this.saleService.checkOut(cartCode)
        return ResponseEntity.status(HttpStatus.CREATED).body(SaleView(sale))
    }

    @PostMapping("/payment")
    fun salePayment(
        @RequestParam(value = "cartCode") cartCode: UUID,
        @RequestParam(value = "totalPaid") totalPaid: Double,
    ) : ResponseEntity<SaleView> {
        val sale: Sale = this.saleService.payment(cartCode, totalPaid)
        return ResponseEntity.status(HttpStatus.OK).body(SaleView(sale))
    }

}