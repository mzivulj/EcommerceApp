package com.shop.ecommerce.controller;

import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:8081/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    @PostMapping(value="/create2", consumes = "application/json")
    public String createCategory2() {
        categoryService.createCategory2();
        return "Success";
    }

    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @RequestBody Category category) {
        // Check to see if the category exists.
        if (Objects.nonNull(categoryService.readCategory(categoryID))) {
            // If the category exists then update it.
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }

        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}