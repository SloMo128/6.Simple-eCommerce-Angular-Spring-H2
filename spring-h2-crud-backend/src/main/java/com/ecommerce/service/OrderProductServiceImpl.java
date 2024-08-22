package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.OrderProduct;
import com.ecommerce.entities.Product;
import com.ecommerce.repository.OrderProductRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;


@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProductToOrder(Long orderId, Long productId, Integer quantity) {
        // Recupera l'ordine dal repository
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        // Recupera il prodotto dal repository
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        // Crea un nuovo oggetto OrderProduct
        OrderProduct orderProduct = new OrderProduct(order, product, quantity);

        // Salva l'oggetto OrderProduct
        orderProductRepository.save(orderProduct);
    }
    
    @Override
    public Page<OrderProduct> getAllOrderProducts(Pageable pageable) {
        return orderProductRepository.findAll(pageable);
    }
}
