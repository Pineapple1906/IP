package com.example.demo.supply.Supplier;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{id}")
    public SupplierDto getSupplier(@PathVariable Long id) {
        return new SupplierDto(supplierService.findSupplier(id));
    }

    @GetMapping("/")
    public List<SupplierDto> getSuppliers() {
        return supplierService.findAllSuppliers().stream().map(SupplierDto::new).toList();
    }

    @PostMapping("/")
    public SupplierDto createSupplier(@RequestParam() String name,
                                   @RequestParam() int license) {
        return new SupplierDto(supplierService.addSupplier(name, license));
    }

    @PatchMapping("/{id}")
    public SupplierDto updateSupplier(@PathVariable Long id,
                                   @RequestParam() String name,
                                   @RequestParam() int license) {
        return new SupplierDto(supplierService.updateSupplier(id, name, license));
    }

    @DeleteMapping("/{id}")
    public SupplierDto deleteSupplier(@PathVariable Long id) {
        return new SupplierDto(supplierService.deleteSupplier(id));
    }
}
