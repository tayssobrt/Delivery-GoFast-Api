package com.Delivery.GoFast.dtos.response;

public record AddressDtoResponse(
        Long id,
        Long cep,
        String street,
        String city,
        String state,
        String neighborhood,
        String number,
        String complement,
        UserDtoResponse user
) {}
