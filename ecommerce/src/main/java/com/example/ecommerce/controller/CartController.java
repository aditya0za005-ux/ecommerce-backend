package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/{cartid}/items")
    public ResponseEntity<CartItem> AddToCart(@PathVariable Long cartid, @RequestParam Long productId, @RequestParam Integer quantity){
        CartItem cartItem =cartService.AddToCart(cartid,productId,quantity);
        return ResponseEntity.ok(cartItem);
    }
    @PostMapping("user/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId){
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }
}
