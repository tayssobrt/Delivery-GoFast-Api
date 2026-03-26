package com.Delivery.GoFast.dtos.request;

import com.Delivery.GoFast.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDtoRequest(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Description is required")
    String description,

    @NotNull(message = "Price is required")
    Double price,

    @NotBlank(message = "Category is required")
    ProductCategory category
){}
