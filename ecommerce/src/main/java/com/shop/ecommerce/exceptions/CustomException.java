package com.shop.ecommerce.exceptions;

public class CustomException extends IllegalArgumentException {
    public CustomException(String msg) {
        super(msg);
    }

}
