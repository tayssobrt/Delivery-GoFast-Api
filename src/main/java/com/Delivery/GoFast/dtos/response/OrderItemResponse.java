package com.Delivery.GoFast.dtos.response;

import com.Delivery.GoFast.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long id;
    private ProductResponse product;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    // From entity serve para converter a entity na response DTO
    public static OrderItemResponse fromEntity(OrderItem orderItem){
        OrderItemResponse response = new OrderItemResponse();

        response.setId(orderItem.getId());
        response.setProduct(ProductResponse.fromEntity(orderItem.getProduct()));
        response.setQuantity(orderItem.getQuantity());
        response.setUnitPrice(orderItem.getUnitPrice());
        response.setTotalPrice(orderItem.getTotalPrice());

        return response;
    }

}
