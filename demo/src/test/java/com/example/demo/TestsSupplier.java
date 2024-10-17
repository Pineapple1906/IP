package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestsSupplier {
//    @Autowired
//    private SupplierService supplierService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private ProductService productService;
//
//    @Test
//    void testSupplier(){
//        supplierService.deleteAll();
//        final Supplier supplier = supplierService.addSupplier("SuperSup", 359342);
//        Assertions.assertNotNull(supplier.getId());
//    }
//
//    @Test
//    void testSupplierRead(){
//        supplierService.deleteAll();
//        final Supplier supplier = supplierService.addSupplier("Huawei", 4357695);
//        final Supplier findSupplier = supplierService.findSupplier(supplier.getId());
//        Assertions.assertEquals(supplier, findSupplier);
//    }
//
//    @Test
//    void testSupplierReadNotFound(){
//        supplierService.deleteAll();
//        Assertions.assertThrows(EntityNotFoundException.class, () -> supplierService.findSupplier(-1L));
//    }
//
//    @Test
//    void testSupplierReadAll(){
//        supplierService.deleteAll();
//        supplierService.addSupplier("Samsung", 3485456);
//        supplierService.addSupplier("Huawei", 45736964);
//        final List<Supplier> suppliers = supplierService.findAllSuppliers();
//        Assertions.assertEquals(suppliers.size(), 2);
//    }
//
//    @Test
//    void testSupplierReadAllEmpty(){
//        supplierService.deleteAll();
//        final List<Supplier> suppliers = supplierService.findAllSuppliers();
//        Assertions.assertEquals(suppliers.size(), 0);
//    }
}
