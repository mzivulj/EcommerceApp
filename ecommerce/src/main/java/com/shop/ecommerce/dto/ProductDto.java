package com.shop.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String imageURL;
    private double price;
    private String description;
    private Integer categoryId;
}

