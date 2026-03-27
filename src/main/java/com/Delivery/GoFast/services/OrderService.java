package com.Delivery.GoFast.services;

import com.Delivery.GoFast.entities.Order;
import com.Delivery.GoFast.entities.OrderItem;
import com.Delivery.GoFast.entities.Product;
import com.Delivery.GoFast.enums.OrderStatus;
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

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // ? Cria uma Order
    public Order createOrder(Order order) {

        // * Percorre os itens da order
        for (OrderItem item : order.getOrderItems()) {
            //  * valida se o produto é nulo
            if (item.getProduct() == null) {
                throw new RuntimeException("Product not found");
            }
            //  * valida se o produto tem ID
            if (item.getProduct().getId() == null) {
                throw new RuntimeException("Product without ID");
            }

            if (item.getProduct().getActive() == false) {
                throw new RuntimeException("Product is not active");
            }
        }

        // * Busca os produtos
        List<Long> productIds = order.getOrderItems()
                .stream()
                .map(item -> item.getProduct().getId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        // * Verifica se todos os produtos existem
        if (products.size() != productIds.size()) {
            throw new RuntimeException("Algum produto não existe");
        }

        BigDecimal total = BigDecimal.ZERO;

        // * Calcula o total da order
        for (OrderItem item : order.getOrderItems()) {

            Product product = null;

            BigDecimal price = product.getPrice();
            int quantity = item.getQuantity();

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity));

            total = total.add(itemTotal);
        }

        order.setOrderPrice(total);
        order.setOrderStatus(OrderStatus.REVIEW);
        return orderRepository.save(order);
    }

    public void CancelOrder(Order order) {

        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        if (order.getId() == null) {
            throw new RuntimeException("Order without ID");
        }

        Optional<Order> orderOptional = orderRepository.findById(order.getId());

        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        if (orderOptional.get().getOrderStatus() != OrderStatus.REVIEW) {
            throw new RuntimeException("Order is not in review");
        }

        orderRepository.delete(orderOptional.get());
    }

}
