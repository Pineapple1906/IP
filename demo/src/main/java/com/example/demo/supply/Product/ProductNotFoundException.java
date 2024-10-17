package com.example.demo.supply.Product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super(String.format("Product with id [%s] is not found", id));
    }
}
