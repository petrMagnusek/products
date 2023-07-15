package org.example.product.controller;

import org.example.product.dtos.Product;
import org.example.product.repository.ProductRepository;
import org.example.product.repository.impl.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@ComponentScan({"org.example.product.repository"})
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(ProductController.class, args);
    //    System.out.println("Hello world!");
    }



    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = productRepository.getAllProducts();
        return products;
    }
}