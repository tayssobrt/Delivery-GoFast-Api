package com.Delivery.GoFast.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDtoRequest(

    @NotEmpty(message = "Items is required")
    @Valid //valida os itens dentro da lista
    List<OrderItemDtoRequest> items,

    @NotNull(message = "Address is required")
    Long addressRequest,

    String label
){}
