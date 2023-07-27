package com.testshop.testshop.controller;

import com.testshop.testshop.model.Product;
import com.testshop.testshop.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = productRepository.findAll();

        logger.info("Received get all products request");

        if (products.isEmpty()){
            logger.info("No products found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            logger.info("Returned all products");
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long id){
        logger.info("Received get single product by id request");

        Product product = productRepository.findById(id).orElse(null);

        if (product == null){
            logger.info("Product is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Returned product info");
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        logger.info("Received post product request");
        product.setId(null);
        Product uploaded = productRepository.save(product);
        logger.info("Added new product");
        return new ResponseEntity<>(uploaded, HttpStatus.OK);
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<Product>  updateProduct(@PathVariable Long id, @RequestBody Product product){
        logger.info("Received update product request");

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null){
            logger.info("Product is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setActive(product.isActive());
            existingProduct.setCreatedAt(product.getCreatedAt());
            existingProduct.setUpdatedAt(LocalDateTime.now());

            Product updated = productRepository.save(existingProduct);

            logger.info("Updated product");
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        logger.info("Received delete product request");
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Product deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.info("Product is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
