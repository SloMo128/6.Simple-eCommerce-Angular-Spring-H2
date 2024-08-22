package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.OrderProduct;
import com.ecommerce.service.OrderProductService;
import com.ecommerce.service.OrderService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderProductService orderProductService;
    
    @Autowired
    private OrderService orderService;

    @PostMapping("/{orderId}/products/{productId}")
    public ResponseEntity<String> addProductToOrder(
            @PathVariable Long orderId,
            @PathVariable Long productId,
            @RequestParam(name = "quantity", required = false) Integer quantity) {

        try {
            // Chiama il servizio per aggiungere il prodotto all'ordine
            orderProductService.addProductToOrder(orderId, productId, quantity);
            return new ResponseEntity<>(null ,HttpStatus.CREATED);

        } catch (Exception e) {
            // Gestisce altri tipi di eccezioni
        	throw new RuntimeException("Error in connecting the product to the order", e);
        }
    }

    
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }
    
    @GetMapping("/order-products")
    public ResponseEntity<Page<OrderProduct>> getAllOrderProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "orderId") String sort,
            @RequestParam(name="order", required = false, defaultValue = "DESC") String order){
        
	    Sort.Direction sortDirection = "DESC".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        Page<OrderProduct> orderProducts = orderProductService.getAllOrderProducts(paging);
        return ResponseEntity.ok(orderProducts);
    }
    
    
}

