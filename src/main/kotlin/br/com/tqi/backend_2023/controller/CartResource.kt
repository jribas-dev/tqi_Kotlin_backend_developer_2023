package br.com.tqi.backend_2023.controller

import br.com.tqi.backend_2023.dto.request.CartItemsDto
import br.com.tqi.backend_2023.dto.response.CartItemsView
import br.com.tqi.backend_2023.entity.Cart
import br.com.tqi.backend_2023.service.impl.CartItemsService
import br.com.tqi.backend_2023.service.impl.CartService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cart")
class CartResource (
    private val cartService: CartService,
    private val cartItemsService: CartItemsService
) {

    @PostMapping
    fun saveItem(
        @RequestBody
        @Valid cartItemsDto: CartItemsDto
    ) : ResponseEntity<CartItemsView> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            CartItemsView(this.cartItemsService.save(cartItemsDto.toEntity())
            )
        )
    }

    @GetMapping("/new")
    fun newCart(): ResponseEntity<Cart> {
       return ResponseEntity.status(HttpStatus.OK).body(cartService.new())
    }
}