package com.Delivery.GoFast.repositories;

import com.Delivery.GoFast.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
