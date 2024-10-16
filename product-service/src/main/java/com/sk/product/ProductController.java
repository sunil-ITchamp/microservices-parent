package com.sk.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedOrder = productRepository.save(product);
        return ResponseEntity.ok(savedOrder);
    }


    //Read
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product foundProduct = productRepository.findById(id).
                    orElseThrow(() -> new RuntimeException("Product not found with id = " + id));
            return ResponseEntity.ok(foundProduct);
        } catch (Exception e) {
            log.error("Exception - " + e.getMessage());
            return ResponseEntity.ok("Exception - Order not found with id = " + id);
        }

    }

    //Read
    @GetMapping()
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/search/{name}/{description}")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getProductByNameAndDescription(@PathVariable String name, @PathVariable String description) {
        System.out.println(name + " and " + description);
        log.debug(" name = " + name + " : description = " + description);
        Stream<Product> byNameAndDescription = productRepository.findByNameAndDescription(name, description);
        return ResponseEntity.ok(byNameAndDescription.collect(Collectors.toList()));
    }

}

