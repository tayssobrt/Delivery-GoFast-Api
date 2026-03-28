package com.Delivery.GoFast.controllers;

import com.Delivery.GoFast.dtos.request.OrderDtoRequest;
import com.Delivery.GoFast.dtos.response.OrderDtoResponse;
import com.Delivery.GoFast.entities.Order;
import com.Delivery.GoFast.mappers.OrderMapper;
import com.Delivery.GoFast.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDtoResponse> createOrder(@Valid @RequestBody OrderDtoRequest order) {
        Order result = orderService.createOrder(order);
        return ResponseEntity.status(201).body(orderMapper.toResponse(result));
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

}
