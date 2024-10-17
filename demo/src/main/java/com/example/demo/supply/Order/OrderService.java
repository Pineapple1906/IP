package com.example.demo.supply.Order;

import com.example.demo.supply.Product.Product;
import com.example.demo.supply.Product.ProductService;
import com.example.demo.supply.Supplier.Supplier;
import com.example.demo.supply.Supplier.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final SupplierService supplierService;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService,
                        SupplierService supplierService)
    {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @Transactional
    public Order addOrder(Long supplierId){
        final Order order = new Order(new Date());
        order.setSupplier(supplierService.findSupplier(supplierId));
        return orderRepository.save(order);
    }

    @Transactional
    public List<Supplier> suppliers(Long productId){
        final Product product = productService.findProduct(productId);
        return orderRepository.getSomeSuppliers(product);
    }

    @Transactional
    public Order addProduct(Long id, Long productId) {
        final Order currentOrder = findOrder(id);
        currentOrder.addProduct(productService.findProduct(productId));
        return orderRepository.save(currentOrder);
    }

    @Transactional
    public Order removeProduct(Long id, Long productId) {
        final Order currentOrder = findOrder(id);
        currentOrder.addProduct(productService.findProduct(productId));
        return orderRepository.save(currentOrder);
    }

    @Transactional(readOnly = true)
    public Order findOrder(Long id) {
        final Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> findAllOrderProducts(Long orderId) {
        final Optional<Order> order = orderRepository.findById(orderId);
        return order.orElseThrow(() -> new OrderNotFoundException(orderId)).getProducts();
    }

    @Transactional
    public Order deleteOrder(Long id) {
        final Order currentOrder = findOrder(id);
        orderRepository.delete(currentOrder);
        return currentOrder;
    }

    @Transactional
    public void deleteAll(){
        orderRepository.deleteAll();
    }
}
