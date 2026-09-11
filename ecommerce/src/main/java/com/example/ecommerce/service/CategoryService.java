package com.example.ecommerce.service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(Category category){
            return categoryRepository.save(category);
    }
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow();
    }
    public Category updateCategory(Long id, Category updateCategory){
        Category existingCategory = categoryRepository.findById(id).orElseThrow();
        existingCategory.setName(updateCategory.getName());
        return categoryRepository.save(existingCategory);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
