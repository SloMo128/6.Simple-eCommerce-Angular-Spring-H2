package com.ecommerce.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Order;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        // Puoi aggiungere qui ulteriori logiche, come impostare lo stato iniziale o la data di creazione
    	String status = order.getStatus();
    	if("PAID".equalsIgnoreCase(status)) {
    		order.setDataCreated(LocalDate.now());  // Imposta la data di creazione come la data corrente
        	order.setStatus("PAID");  // Imposta uno stato iniziale, ad esempio "CREATED"

        	// Salva l'ordine nel database
        	return orderRepository.save(order);
    	}else {
    		throw new ResourceNotFoundException("Order non creato");
    	}
    }
}
