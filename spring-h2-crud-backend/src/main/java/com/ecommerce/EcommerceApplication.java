package com.ecommerce;

import com.ecommerce.entities.Product;
import com.ecommerce.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
/*
            productService.save(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
            productService.save(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
            productService.save(new Product(3L, "Sofa", 100.00, "http://placehold.it/200x100"));
            productService.save(new Product(4L, "Icecream", 5.00, "http://placehold.it/200x100"));
            productService.save(new Product(5L, "Beer", 3.00, "http://placehold.it/200x100"));
            productService.save(new Product(6L, "Phone", 500.00, "http://placehold.it/200x100"));
            productService.save(new Product(7L, "Watch", 30.00, "http://placehold.it/200x100"));
 */
            productService.save(new Product(1L, "TV Set", 2300.00, "https://www.svgrepo.com/show/289354/tv-screen.svg"));
            productService.save(new Product(2L, "Game Console", 200.00, "https://www.svgrepo.com/show/288634/game-console-psp.svg"));
            productService.save(new Product(3L, "Sofa", 1100.00, "https://www.svgrepo.com/show/475085/sofa.svg"));
            productService.save(new Product(4L, "Icecream", 5.00, "https://www.svgrepo.com/show/475122/icecream.svg"));
            productService.save(new Product(5L, "Beer", 3.00, "https://www.svgrepo.com/show/475542/beer.svg"));
            productService.save(new Product(6L, "Phone", 500.00, "https://www.svgrepo.com/show/533432/phone-office.svg"));
            productService.save(new Product(7L, "Watch", 300.00, "https://www.svgrepo.com/show/508324/watch.svg"));
        };
    }
}

