package com.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.OrderProduct;

@Service
public interface OrderProductService {
    void addProductToOrder(Long orderId, Long productId, Integer quantity);
    
    Page<OrderProduct> getAllOrderProducts(Pageable pageable);
}
