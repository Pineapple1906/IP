package com.example.demo.supply.Order;

import com.example.demo.supply.Product.Product;
import com.example.demo.supply.Product.ProductDto;
import com.example.demo.supply.Supplier.Supplier;
import com.example.demo.supply.Supplier.SupplierDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return new OrderDto(orderService.findOrder(id));
    }

    @GetMapping("/")
    public List<OrderDto> getOrders() {
        return orderService.findAllOrders().stream().map(OrderDto::new).toList();
    }

    @GetMapping("/getProducts/{id}")
    public List<ProductDto> getOrderProducts(@PathVariable Long id) {
        return orderService.findAllOrderProducts(id).stream().map(ProductDto::new).toList();
    }

    @GetMapping("/someSuppliers/{id}")
    public List<SupplierDto> getSomeSuppliers(@PathVariable Long id) {
        return orderService.suppliers(id).stream().map(SupplierDto::new).toList();
    }

    @PostMapping("/")
    public Long createOrder(@RequestParam() Long supplierId) {
        return new OrderDto(orderService.addOrder(supplierId)).getId();
    }

    @PatchMapping("/addProduct/{id}")
    public OrderDto addProduct(@PathVariable Long id,
                               @RequestParam() Long productId){
        return new OrderDto(orderService.addProduct(id, productId));
    }

    @PatchMapping("/removeProduct/{id}")
    public OrderDto removeProduct(@PathVariable Long id,
                               @RequestParam() Long productId){
        return new OrderDto(orderService.removeProduct(id, productId));
    }

    @DeleteMapping("/{id}")
    public OrderDto deleteOrder(@PathVariable Long id) {
        return new OrderDto(orderService.deleteOrder(id));
    }
}
