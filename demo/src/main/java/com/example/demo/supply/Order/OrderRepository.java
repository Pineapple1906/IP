package com.example.demo.supply.Order;

import com.example.demo.supply.Product.Product;
import com.example.demo.supply.Supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.supplier FROM Order o  where ?1 member of o.products")
    List<Supplier> getSomeSuppliers(Product product);
}
