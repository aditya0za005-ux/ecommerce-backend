package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product productSaved = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> get = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(get);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product get1 = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(get1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id,@RequestBody Product product){
        Product update = productService.updateProductById(id,product);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteProductById(@PathVariable Long id){
            productService.deleteProductById(id);
        return ResponseEntity.ok(Map.of("message" , "Product deleted successfully"));
    }
}
