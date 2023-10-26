package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.ProductDto;
import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.service.CategoryService;
import com.shop.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:8081/")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping ("/list")
    public ResponseEntity<ProductDto> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity(products,HttpStatus.OK);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody ProductDto productDto) {
        // Check to see if the category exists.
        if (Objects.nonNull(productService.readProduct(productID))) {
            // If the category exists then update it.
            productService.updateProduct( productID, productDto);
            return new ResponseEntity<>(new ApiResponse(true, "updated the product"), HttpStatus.OK);
        }
        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(false, "product does not exist"), HttpStatus.NOT_FOUND);
    }
}
