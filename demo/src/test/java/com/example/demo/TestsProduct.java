package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestsProduct {
//    @Autowired
//    private ProductService productService;
//
//
//    @Test
//    void testProduct(){
//        productService.deleteAll();
//        final Product product = productService.addProduct("Huawei Band 3", 2000.13);
//        Assertions.assertNotNull(product.getId());
//    }
//
//    @Test
//    void testProductRead(){
//        productService.deleteAll();
//        final Product product = productService.addProduct("Huawei Band 3", 2000.13);
//        final Product findProduct = productService.findProduct(product.getId());
//        Assertions.assertEquals(product, findProduct);
//    }
//
//    @Test
//    void testProductReadNotFound(){
//        productService.deleteAll();
//        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.findProduct(-1L));
//    }
//
//    @Test
//    void testProductReadAll(){
//        productService.deleteAll();
//        productService.addProduct("Samsung A3", 22000.4);
//        productService.addProduct("Huawei Band 3", 2000.13);
//        final List<Product> products = productService.findAllProducts();
//        Assertions.assertEquals(products.size(), 2);
//    }
//
//    @Test
//    void testProductReadAllEmpty(){
//        productService.deleteAll();
//        final List<Product> products = productService.findAllProducts();
//        Assertions.assertEquals(products.size(), 0);
//    }
}
