package com.example.demo.supply.Supplier;

import com.example.demo.supply.Order.Order;

import java.util.List;

public class SupplierDto {
    private Long id;
    private String name;
    private int license;
    private List<Order> orders;

    public SupplierDto(){
    }

    public SupplierDto(Supplier supplier){
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.license = supplier.getLicense();
        this.orders = supplier.getOrders();
    }

    public Long getId() { return id; }
    public String getName() {
        return name;
    }
    public int getLicense() {
        return license;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
