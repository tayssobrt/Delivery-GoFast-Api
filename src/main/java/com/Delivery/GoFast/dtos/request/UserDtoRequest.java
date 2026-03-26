package com.Delivery.GoFast.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDtoRequest(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password,

    @Pattern(regexp = "\\d{10,11}", message = "Phone must be 10 or 11 digits")
    String phone,

    @Valid //Importante para validar as regras de validação do AddressRequest
    AddressDtoRequest address

){}
