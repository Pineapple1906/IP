package com.example.demo.supply.Order;

import com.example.demo.supply.Product.Product;
import com.example.demo.supply.Supplier.Supplier;

import java.util.Date;
import java.util.List;

public class OrderDtoForCreate {
    private Long id;
    private Date dateOfOrder;
    private long supplierId;
    private List<Long> productsId;

    public OrderDtoForCreate(){
    }

    public OrderDtoForCreate(Order order){
        this.id = order.getId();
        this.dateOfOrder = order.getDateOfOrder();
        this.supplierId = order.getSupplier().getId();
        this.productsId = order.getProducts().stream().map(Product::getId).toList();
    }

    public long getId() {
        return id;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }
}
