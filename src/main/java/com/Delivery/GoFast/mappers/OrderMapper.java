package com.Delivery.GoFast.mappers;

import com.Delivery.GoFast.dtos.request.OrderDtoRequest;
import com.Delivery.GoFast.dtos.request.OrderItemDtoRequest;
import com.Delivery.GoFast.dtos.response.OrderDtoResponse;
import com.Delivery.GoFast.dtos.response.OrderItemDtoResponse;
import com.Delivery.GoFast.dtos.response.ProductDtoResponse;
import com.Delivery.GoFast.dtos.response.UserDtoResponse;
import com.Delivery.GoFast.entities.Order;
import com.Delivery.GoFast.entities.OrderItem;
import com.Delivery.GoFast.entities.Product;
import com.Delivery.GoFast.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    // * Request -> Entidade
    public Order toEntity(OrderDtoRequest dto, List<Product> products) {
        Order order = new Order();
        order.setLabel(dto.label());
        order.setOrderItems(toOrderItems(dto.orderItems(), products, order));
        return order;
    }

    private List<OrderItem> toOrderItems(List<OrderItemDtoRequest> itemDtos, List<Product> products, Order order) {
        return itemDtos.stream()
                .map(itemDto -> {
                    Product product = products.stream()
                            .filter(p -> p.getId().equals(itemDto.productId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Product not found: " + itemDto.productId()));

                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setProduct(product);
                    item.setQuantity(itemDto.quantity());
                    item.setUnitPrice(product.getPrice());
                    item.setTotalPrice(product.getPrice().multiply(java.math.BigDecimal.valueOf(itemDto.quantity())));
                    return item;
                })
                .toList();
    }

    // * Entidade -> Response
    public OrderDtoResponse toResponse(Order order) {
        return new OrderDtoResponse(
                order.getId(),
                toUserResponse(order.getOrderUser()),
                toUserResponse(order.getDeliveryPerson()),
                toOrderItemsResponse(order.getOrderItems()),
                order.getOrderedAt(),
                order.getOrderPrice(),
                order.getOrderStatus(),
                order.getOrderAddress(),
                order.getLabel(),
                order.getAddress()
        );
    }

    private UserDtoResponse toUserResponse(User user) {
        if (user == null) return null;
        return new UserDtoResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    private List<OrderItemDtoResponse> toOrderItemsResponse(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemDtoResponse(
                        item.getId(),
                        toProductResponse(item.getProduct()),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getTotalPrice()
                ))
                .toList();
    }

    private ProductDtoResponse toProductResponse(Product product) {
        if (product == null) return null;
        return new ProductDtoResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getActive()
        );
    }
}