package com.shop.ecommerce.service;

import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryrepository;

    public List<Category> listCategories() {
        return categoryrepository.findAll();
    }

    public void createCategory(Category category) {
        categoryrepository.save(category);
    }

    public void createCategory2() {
        Category category3 = new Category("1","2","test.com");
        categoryrepository.save(category3);
    }

    public Category readCategory(String categoryName) {
        return categoryrepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryrepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryrepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        categoryrepository.save(category);
    }
}
