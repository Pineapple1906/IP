package com.example.demo.supply.Supplier;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(Long id){
        super(String.format("Supplier with id [%s] is not found", id));
    }

}
