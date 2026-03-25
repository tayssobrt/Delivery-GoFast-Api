package com.Delivery.GoFast.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{8}", message = "CEP must have 8 digits")
    private String cep;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Number is required")
    private String number;

    private String complement;

    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(regexp = "[A-Z]{2}", message = "State must be 2 letters")
    private String state;
}
