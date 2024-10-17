package com.example.demo.supply.Order;

import com.example.demo.supply.Product.Product;
import com.example.demo.supply.Supplier.Supplier;


import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long id;
    private Date dateOfOrder;
    private Supplier supplier;
    private List<Product> products;

    public OrderDto(){
    }
    public OrderDto(Order order){
        this.id = order.getId();
        this.dateOfOrder = order.getDateOfOrder();
        this.supplier = order.getSupplier();
        this.products = order.getProducts();
    }

    public Long getId() {
        return id;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
