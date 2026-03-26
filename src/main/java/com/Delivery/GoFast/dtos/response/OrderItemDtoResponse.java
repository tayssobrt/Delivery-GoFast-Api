package com.Delivery.GoFast.dtos.response;

import java.math.BigDecimal;

public record OrderItemDtoResponse(
     Long id,
     ProductDtoResponse product,
     Integer quantity,
     BigDecimal unitPrice,
     BigDecimal totalPrice
){}
