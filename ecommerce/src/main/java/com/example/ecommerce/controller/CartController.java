package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/{cartid}/items")
    public ResponseEntity<CartItem> AddToCart(@PathVariable Long cartid, @RequestParam Long productId, @RequestParam Integer quantity){
        CartItem cartItem =cartService.addToCart(cartid,productId,quantity);
        return ResponseEntity.ok(cartItem);
    }
    @PostMapping("user/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId){
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId){
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId){
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,@RequestParam Integer quantity){
        CartItem cartItem = cartService.updateCartItemById(cartItemId,quantity);
        return ResponseEntity.ok(cartItem);
    }
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Map<String,String>> removeCartItem(@PathVariable Long cartItemId){
        cartService.removeCartItemById(cartItemId);
        return ResponseEntity.ok(Map.of("message","Cart item removed successfully"));
    }
}
