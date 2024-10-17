package com.example.demo.supply.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(String name, double cost){
        final Product product = new Product(name, cost);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product findProduct(Long id) {
        final Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updateProduct(Long id, String name, double cost) {
        final Product currentProduct = findProduct(id);
        currentProduct.setName(name);
        currentProduct.setCost(cost);
        return productRepository.save(currentProduct);
    }

    @Transactional
    public Product deleteProduct(Long id) {
        final Product product = findProduct(id);
        productRepository.delete(product);
        return  product;
    }

    @Transactional
    public void deleteAll(){
        productRepository.deleteAll();
    }
}
