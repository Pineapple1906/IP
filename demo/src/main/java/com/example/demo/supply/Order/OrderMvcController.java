package com.example.demo.supply.Order;

import com.example.demo.supply.Product.ProductDto;
import com.example.demo.supply.Product.ProductService;
import com.example.demo.supply.Supplier.SupplierDto;
import com.example.demo.supply.Supplier.SupplierService;
import com.example.demo.supply.User.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderMvcController {
    private OrderService orderService;
    private ProductService productService;
    private SupplierService supplierService;

    public OrderMvcController(OrderService orderService,
                              ProductService productService,
                              SupplierService supplierService){
        this.orderService = orderService;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getOrders(Model model) {
        model.addAttribute("orders",
                orderService.findAllOrders().stream()
                        .map(OrderDto::new)
                        .toList());
        return "order";
    }

    @GetMapping("/dop")
    public String getSuppliers(Model model,
                               @RequestParam(required = false)  Long id) {
        if(id == null || id <= 0) {
            model.addAttribute("productDto", new ProductDto());
            model.addAttribute("suppliers", null);
            model.addAttribute("selectedProduct", null);
        }
        else{
            ProductDto product = new ProductDto(productService.findProduct(id));
            model.addAttribute("productDto", product);
            model.addAttribute("selectedProduct", null);
            model.addAttribute("suppliers", orderService.suppliers(id).stream().map(SupplierDto::new).toList());
        }
        model.addAttribute("products", productService.findAllProducts().stream().map(ProductDto::new).toList());
//        model.addAttribute("orderDto", new OrderDto(orderService.findOrder(id)));
        return "order-dop";
    }

    @GetMapping("/add")
    @Secured({UserRole.AsString.ADMIN})
    public String addOrder(Model model) {
        model.addAttribute("orderDto", new OrderDtoForCreate());
        model.addAttribute("selectedSupplier", null);
        model.addAttribute("suppliers", supplierService.findAllSuppliers().stream().map(SupplierDto::new).toList());
        model.addAttribute("products", productService.findAllProducts().stream().map(ProductDto::new).toList());
        return "order-add";
    }

    @PostMapping("/create")
    @Secured({UserRole.AsString.ADMIN})
    public String saveOrder(Model model,
                            @ModelAttribute("orderDto") @Valid OrderDtoForCreate order,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "order-add";
        }
        Order newOrder = orderService.addOrder(supplierService.findSupplier(order.getSupplierId()).getId());
        for(Long obj: order.getProductsId())
            orderService.addProduct(newOrder.getId(), obj);
        return "redirect:/order";
    }
}
