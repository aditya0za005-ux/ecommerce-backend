package com.example.ecommerce.service;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }
    @Transactional
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

        cartItemRepository.deleteByCartId(cart.getId());


        return order;
    }
    public List<Order> getOrderByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }
    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow();
    }
    public Order updateOrderById(Long orderId, String status){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
