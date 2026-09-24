package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long userId){
        Order order = orderService.createOrder(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Long userId){
        List<Order> orders = orderService.getOrderByUserId(userId);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderById(@PathVariable Long orderId,@RequestParam String status){
        Order order = orderService.updateOrderById(orderId,status);
        return ResponseEntity.ok(order);
    }
}
