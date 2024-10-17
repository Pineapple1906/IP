package com.example.demo.supply.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Supplier addSupplier(String name, int license){
        final Supplier supplier = new Supplier(name, license);
        return supplierRepository.save(supplier);
    }

    @Transactional(readOnly = true)
    public Supplier findSupplier(Long id) {
        final Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElseThrow(() -> new SupplierNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Transactional
    public Supplier updateSupplier(Long id, String name, int license) {
        final Supplier currentSupplier = findSupplier(id);
        currentSupplier.setName(name);
        currentSupplier.setLicense(license);
        return supplierRepository.save(currentSupplier);
    }

    @Transactional
    public Supplier deleteSupplier(Long id) {
        final Supplier currentSupplier = findSupplier(id);
        supplierRepository.delete(currentSupplier);
        return currentSupplier;
    }

    @Transactional
    public void deleteAll(){
        supplierRepository.deleteAll();
    }
}
