package com.example.ecommerce.service;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public Order createOrder(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findById(userId).orElseThrow();
        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItem()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            order.getOrderItems().add(orderItem);

            BigDecimal itemTotal =
                    cartItem.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
        }

        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);

        orderItemRepository.saveAll(order.getOrderItems());

        cart.getCartItem().clear();

        return order;
    }
}
