package com.Delivery.GoFast.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderDtoRequest(

    @NotEmpty(message = "Items is required")
    @Valid //valida os itens dentro da lista
    List<OrderItemDtoRequest> orderItems,

    @NotNull(message = "Address is required")
    Long addressRequest,

    String label
){}
