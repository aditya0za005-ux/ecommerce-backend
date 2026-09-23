package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
}
