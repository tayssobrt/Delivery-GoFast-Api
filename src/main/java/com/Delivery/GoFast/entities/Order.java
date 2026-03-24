package com.Delivery.GoFast.entities;

import com.Delivery.GoFast.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User orderUser;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private User deliveryPerson;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;

    @Positive
    @Column(name = "order_price", nullable = false)
    private BigDecimal orderPrice;

    @Enumerated
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_address", nullable = false)
    private String orderAddress;

}
