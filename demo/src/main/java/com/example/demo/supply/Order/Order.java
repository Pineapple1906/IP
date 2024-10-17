package com.example.demo.supply.Order;

import com.example.demo.supply.Product.Product;
import com.example.demo.supply.Supplier.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfOrder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "supplier_fk")
    private Supplier supplier;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ordersAndProducts",joinColumns = @JoinColumn(name = "order_fk"),
                inverseJoinColumns = @JoinColumn(name = "product_fk"))
    private List<Product> products;

    public Long getId(){
        return id;
    }
    public Order(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
        products = new ArrayList<>();
    }

    public Order() {
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        supplier.getOrders().add(this);
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
            product.getOrders().add(this);
        }
    }

    public void deleteProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
            product.getOrders().remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if(!Objects.equals(id, order.id)) return false;

        if(!Objects.equals(dateOfOrder.toString(), order.dateOfOrder.toString())) return false;
        if(!Objects.equals(supplier, order.supplier)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", date='" + dateOfOrder + '\'' +
                '}';
    }
}
