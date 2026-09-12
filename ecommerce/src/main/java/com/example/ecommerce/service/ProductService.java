package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }
    public Product updateProductById(Long id, Product updatedproduct){
        Product existingProduct = productRepository.findById(id).orElseThrow();
        existingProduct.setProdName(updatedproduct.getProdName());
        existingProduct.setPrice(updatedproduct.getPrice());
        existingProduct.setStock(updatedproduct.getStock());
        return productRepository.save(existingProduct);
    }
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
