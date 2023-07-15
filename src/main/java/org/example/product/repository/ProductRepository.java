package org.example.product.repository;

import org.example.product.dtos.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface ProductRepository {

    List<Product> getAllProducts();

    ResponseEntity createProduct(Product product);

    Product getProductById(long id);

    ResponseEntity deleteProduct(long id);

    ResponseEntity updateProduct(Product product, long id);
}
