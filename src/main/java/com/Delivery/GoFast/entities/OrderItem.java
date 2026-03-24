package com.Delivery.GoFast.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Positive
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Positive
    @Column(name = "unity_price", nullable = false)
    private BigDecimal unitPrice;

    @Positive
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}
