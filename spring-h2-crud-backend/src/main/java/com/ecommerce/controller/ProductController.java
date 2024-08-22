package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.ecommerce.entities.OrderProduct;
import com.ecommerce.entities.Product;
import com.ecommerce.service.ProductService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Object> getProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
            @RequestParam(name="order", required = false, defaultValue = "DESC") String order) {
    	
        
	    Sort.Direction sortDirection = "DESC".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        Page<Product> pageResult = productService.getProducts(paging);
        
        Map<String, Object> responseBody = new LinkedHashMap<>();
        if (pageResult.hasContent()) {
            responseBody.put("products", pageResult.getContent());
            responseBody.put("currentPage", pageResult.getNumber());
            responseBody.put("totalItems", pageResult.getTotalElements());
            responseBody.put("totalPages", pageResult.getTotalPages());
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            responseBody.put("message", "No products found");
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductId(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }
    
    /*@PostMapping("/addcart")
    public ResponseEntity<OrderProduct> addProductToCart(
            @Valid @RequestBody OrderProduct orderProd) {
    	
    	
    	
        if (orderProd.) {
            Post post = postOptional.get();
            comment.setPost(post); // Associa il commento al post
            Comment savedComment = commentRepo.save(comment); // Salva il commento
            
            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("Post not found with id: " + postId);
        }
    }*/
}
