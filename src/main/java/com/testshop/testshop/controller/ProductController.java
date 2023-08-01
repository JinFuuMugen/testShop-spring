package com.testshop.testshop.controller;

import com.testshop.testshop.model.Product;
import com.testshop.testshop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j

public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = productRepository.findAll();

        log.info("Received get all products request");

        if (products.isEmpty()){
            log.info("No products found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            log.info("Returned all products");
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long id){
        log.info("Received get single product by id request");

        Product product = productRepository.findById(id).orElse(null);

        if (product == null){
            log.info("Product is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Returned product info");
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        log.info("Received post product request");
        product.setId(null);
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());
        Product uploaded = productRepository.save(product);
        log.info("Added new product");
        return new ResponseEntity<>(uploaded, HttpStatus.OK);
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        log.info("Received update product request");

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null){
            log.info("Product is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setActive(product.isActive());
            existingProduct.setUpdatedAt(LocalDateTime.now());

            Product updated = productRepository.save(existingProduct);

            log.info("Updated product");
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        log.info("Received delete product request");
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Product is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
