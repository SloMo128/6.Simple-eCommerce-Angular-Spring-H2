package com.ecommerce.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface ProductRepository extends 
		CrudRepository<Product, String>,
		PagingAndSortingRepository<Product, String> {

	Optional<Product> findById(long id);

}