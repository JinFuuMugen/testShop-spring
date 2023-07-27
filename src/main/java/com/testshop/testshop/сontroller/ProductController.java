package com.testshop.testshop.сontroller;

import com.testshop.testshop.model.Product;
import com.testshop.testshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        //TODO : create realization
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long id){
        //TODO : create realization
        return null;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        //TODO : create realization
        return null;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product>  updateProduct(@PathVariable Long id, @RequestBody Product product){
        //TODO : create realization
        return null;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        //TODO : create realization;
        return null;
    }
}
