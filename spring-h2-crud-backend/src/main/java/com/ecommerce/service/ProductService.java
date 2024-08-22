package com.ecommerce.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.ecommerce.entities.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



@Validated
public interface ProductService {

    @NotNull Iterable<Product> getAllProducts();
    
    Page<Product> getProducts(Pageable pageable);
    
    Optional<Product> getProductById(@Min(value = 1L, message = "Invalid product ID.") Long id);

    Product save(Product product);
}
