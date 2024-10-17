package com.example.demo.supply.Supplier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/supplier")
public class SupplierMvcController {
    private final SupplierService supplierService;

    public SupplierMvcController(SupplierService supplierService){ this.supplierService = supplierService;}

    @GetMapping
    public String getSuppliers(Model model) {
        model.addAttribute("suppliers",
                supplierService.findAllSuppliers().stream()
                        .map(SupplierDto::new)
                        .toList());
        return "supplier";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editSuppliers(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("supplierDto", new SupplierDto());
        } else {
            model.addAttribute("supplierId", id);
            model.addAttribute("supplierDto", new SupplierDto(supplierService.findSupplier(id)));
        }
        return "supplier-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveSuppliers(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid SupplierDto supplierDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "supplier-edit";
        }
        if (id == null || id <= 0) {
            supplierService.addSupplier(supplierDto.getName(), supplierDto.getLicense());
        } else {
            supplierService.updateSupplier(id, supplierDto.getName(), supplierDto.getLicense());
        }
        return "redirect:/supplier";
    }

    @PostMapping("/delete/{id}")
    public String deleteSuppliers(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/supplier";
    }
}
