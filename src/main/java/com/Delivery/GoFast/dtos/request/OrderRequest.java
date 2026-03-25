package com.Delivery.GoFast.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "Items is required")
    @Valid //valida os itens dentro da lista
    private List<OrderItemRequest> items;

    @NotNull(message = "Address is required")
    private Long addressRequest;

    private String label;
}
