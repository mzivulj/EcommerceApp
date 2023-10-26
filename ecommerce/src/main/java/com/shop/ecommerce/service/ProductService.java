package com.shop.ecommerce.service;

import com.shop.ecommerce.dto.ProductDto;
import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public Optional<Product> readProduct(Integer productId) {
        return productRepository.findById(productId);
    }

    public void updateProduct(Integer productID, ProductDto newCategory) {
        Product product = productRepository.findById(productID).get();
        product.setDescription(newCategory.getDescription());
        product.setImageURL(newCategory.getImageURL());
        product.setName(newCategory.getName());
        product.setPrice(newCategory.getPrice());
        productRepository.save(product);
    }

    public Product findById(Integer productId) throws Error {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Error("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
}