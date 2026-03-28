package com.Delivery.GoFast.services;

import com.Delivery.GoFast.mappers.OrderMapper;
import com.Delivery.GoFast.dtos.request.OrderDtoRequest;
import com.Delivery.GoFast.dtos.request.OrderItemDtoRequest;
import com.Delivery.GoFast.entities.Order;
import com.Delivery.GoFast.entities.Product;
import com.Delivery.GoFast.enums.OrderStatus;
import com.Delivery.GoFast.exceptions.OrderNotFoundException;
import com.Delivery.GoFast.exceptions.OrderNotInReview;
import com.Delivery.GoFast.repositories.OrderRepository;
import com.Delivery.GoFast.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    ProductRepository productRepository;

    OrderRepository orderRepository;

    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,  OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    // ? Cria uma Order
    public Order createOrder(OrderDtoRequest order) {

        // * Percorre os itens da order
        for (OrderItemDtoRequest item : order.orderItems()) {

            //  * valida se o produto tem ID
            if (item.productId() == null) {
                throw new RuntimeException("Product without ID");
            }
        }

        // * Busca os produtos
        List<Long> productIds = order.orderItems()
                .stream()
                .map(item -> item.productId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        // * Verifica se todos os produtos existem
        if (products.size() != productIds.size()) {
            throw new RuntimeException("Some product doesn't exist");
        }

        products.forEach(p -> {
            if (!p.getActive()) throw new RuntimeException("Product is not active: " + p.getId());
        });
        BigDecimal total = BigDecimal.ZERO;

        // * Calcula o total da order
        for (OrderItemDtoRequest item : order.orderItems()) {

            Product product = products.stream()
                    .filter(p -> p.getId().equals(item.productId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            BigDecimal price = product.getPrice();
            int quantity = item.quantity();

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity));

            total = total.add(itemTotal);
        }

        Order newOrder = orderMapper.toEntity(order,  products);
        newOrder.setOrderPrice(total);
        newOrder.setOrderStatus(OrderStatus.REVIEW);
        return orderRepository.save(newOrder);
    }

        public void cancelOrder(Long orderId) {

        if (orderId == null) {
            throw new OrderNotFoundException("Order not found");
        }

        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order not found");
        }

        if (orderOptional.get().getOrderStatus() != OrderStatus.REVIEW) {
            throw new OrderNotInReview("Order is not in review");
        }

        orderRepository.delete(orderOptional.get());
    }

}
