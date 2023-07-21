package org.example.product.repository.impl;

import org.example.product.dtos.Product;
import org.example.product.exception.NoSuchProductExistsException;
import org.example.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList();
    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public String createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return("BAD");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body has to contain name attribute!");
        }
        try {
            products.add(new Product(getHighestID() +1, product.getName(), product.getPrice(), product.getDescription()));
            //return ResponseEntity.status(HttpStatus.CREATED).body("Product Created Successfully!");
            return("OK");
        } catch (Exception e) {
            return(e.getMessage());
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public String updateProduct(Product product, long id) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return "BAD";
        }
        Optional<Product> prod =  products.stream().filter(prd -> prd.getId() == id).findFirst();
        if (!prod.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            try {
                prod.get().setPrice(product.getPrice());
                prod.get().setName(product.getName());
                prod.get().setDescription(product.getDescription());
                return ("OK");
            } catch (Exception e) {
                return e.getMessage();
            }
        }

    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product =  products.stream().filter(prod -> prod.getId() == id).findFirst();
        if (!product.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            return product.get();
        }
    }

    @Override
    public String deleteProduct(long id) {
        Optional<Product> product =  products.stream().filter(prod -> prod.getId() == id).findFirst();
        if (!product.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            try {
                products.remove(product.get());
                return("OK");
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }

    private long getHighestID() {
        if (products.isEmpty()) {
            return 0;
        } else {
            return products.stream().mapToLong(product -> product.getId()).max().getAsLong();
        }
    }

    public ProductRepositoryImpl() {
        /*Product prod1 = new Product();
        prod1.setId(1);
        prod1.setName("product1");
        Product prod2 = new Product();
        prod2.setId(2);
        prod2.setName("product2");
        products.add(prod1);
        products.add(prod2);*/
    }
}
