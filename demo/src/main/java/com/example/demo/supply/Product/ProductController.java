package com.example.demo.supply.Product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(productService.findProduct(id));
    }

    @GetMapping("/")
    public List<ProductDto> getProducts() {
        return productService.findAllProducts().stream().map(ProductDto::new).toList();
    }

    @PostMapping("/")
    public ProductDto createProduct(@RequestParam() String name,
                                 @RequestParam() double cost) {
        return new ProductDto(productService.addProduct(name, cost));
    }

    @PatchMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam double cost) {
        return new ProductDto(productService.updateProduct(id, name, cost));
    }

    @DeleteMapping("/{id}")
    public ProductDto deleteProduct(@PathVariable Long id) {
        return new ProductDto(productService.deleteProduct(id));
    }
}
