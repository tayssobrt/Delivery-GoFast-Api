package com.Delivery.GoFast.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDtoRequest(
    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{8}", message = "CEP must have 8 digits")
    String cep,

    @NotBlank(message = "Street is required")
    String street,

    @NotBlank(message = "Number is required")
    String number,

    String complement,

    @NotBlank(message = "Neighborhood is required")
    String neighborhood,

    @NotBlank(message = "City is required")
    String city,

    @NotBlank(message = "State is required")
    @Pattern(regexp = "[A-Z]{2}", message = "State must be 2 letters")
    String state
){}
