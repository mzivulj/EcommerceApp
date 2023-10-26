package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.ProductDto;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.model.User;
import com.shop.ecommerce.model.WishList;
import com.shop.ecommerce.service.AuthenticationService;
import com.shop.ecommerce.service.ProductService;
import com.shop.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("http://localhost:8081/")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ProductService productService;


    // save product as wishlist item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Integer productid,
                                                     @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);

        // save the item in wishlist
        Product product = productService.readProduct(productid).get();

        WishList wishList = new WishList(user, product);

        wishListService.createWishlist(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }


    // get all wishlist item for a user

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }



}