package com.Delivery.GoFast.dtos.response;

import com.Delivery.GoFast.entities.Product;
import com.Delivery.GoFast.enums.ProductCategory;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private ProductCategory category;
    private Boolean active;

    public static ProductResponse fromEntity(Product product) {
        if (product == null) return null;

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setActive(product.getActive());
        return response;
    }
}
