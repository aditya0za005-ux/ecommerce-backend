package com.example.ecommerce.service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository,UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    public CartItem addToCart(Long cartId, Long productId, Integer quantity){
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cartId,productId);
        if(existingCartItem.isPresent()){
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
            return cartItemRepository.save(cartItem);
        }
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);

    }
    public Cart createCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        Cart cart = new Cart();

        cart.setUser(user);

        return cartRepository.save(cart);
    }
    public Cart getCart(Long cartId){
        return cartRepository.findById(cartId).orElseThrow();
    }
    public Cart getCartByUserId(Long userId){
        return cartRepository.findByUserId(userId).orElseThrow();
    }
    public CartItem updateCartItemById(Long cartItemId, Integer quantity){
        CartItem existingCart = cartItemRepository.findById(cartItemId).orElseThrow();
        existingCart.setQuantity(quantity);
        return cartItemRepository.save(existingCart);
    }
    public void removeCartItemById(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
        cartItemRepository.delete(cartItem);
    }
}
