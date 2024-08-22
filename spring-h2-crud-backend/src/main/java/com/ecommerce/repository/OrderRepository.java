package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Order;


@Repository
public interface OrderRepository extends 
		CrudRepository<Order, String>,
		PagingAndSortingRepository<Order, String> {

	Optional<Order> findById(Long orderId);

}