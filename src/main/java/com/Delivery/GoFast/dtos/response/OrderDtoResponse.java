package com.Delivery.GoFast.dtos.response;
import com.Delivery.GoFast.entities.Address;
import com.Delivery.GoFast.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDtoResponse(
    Long id,
    UserDtoResponse orderUser,
    UserDtoResponse deliveryPerson,
    List<OrderItemDtoResponse> orderItems,
    LocalDateTime orderedAt,
    BigDecimal orderPrice,
    OrderStatus orderStatus,
    String orderAddress,
    String label,
    Address address
) {}
