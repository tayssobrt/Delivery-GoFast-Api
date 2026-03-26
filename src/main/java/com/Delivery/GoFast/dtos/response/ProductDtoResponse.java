package com.Delivery.GoFast.dtos.response;

import com.Delivery.GoFast.enums.ProductCategory;

import java.math.BigDecimal;

public record ProductDtoResponse(
     Long id,
     String name,
     BigDecimal price,
     String description,
     ProductCategory category,
     Boolean active
){}
