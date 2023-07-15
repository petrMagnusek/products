package org.example.product.repository.impl;

import org.example.product.dtos.Product;
import org.example.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList();
    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    public ProductRepositoryImpl() {
        Product prod1 = new Product();
        prod1.setId(1);
        prod1.setName("product1");
        Product prod2 = new Product();
        prod2.setId(2);
        prod2.setName("product2");
        products.add(prod1);
        products.add(prod2);
    }
}
