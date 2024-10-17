package com.example.demo.supply.Product;

import com.example.demo.supply.Order.Order;

import java.util.List;

public class ProductDto {
    private long id;
    private String name;
    private double cost;
    private List<Order> orders;

    public ProductDto() {}

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.cost = product.getCost();
        this.orders = product.getOrders();
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
