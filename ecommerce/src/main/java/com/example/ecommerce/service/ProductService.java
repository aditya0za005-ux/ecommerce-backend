package com.example.ecommerce.service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public Product assignCategory(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        product.getCategories().add(category);
        return productRepository.save(product);
    }
    public Product removeCategory(Long productId, Long categoryId){
        Product product =productRepository.findById(productId).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        product.getCategories().remove(category);
        return productRepository.save(product);
    }

}
