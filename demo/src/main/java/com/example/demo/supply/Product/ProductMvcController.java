package com.example.demo.supply.Product;

import com.example.demo.supply.User.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/product")
public class ProductMvcController {
    private final ProductService productService;

    public ProductMvcController(ProductService productService){ this.productService = productService;}

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products",
                productService.findAllProducts().stream()
                        .map(ProductDto::new)
                        .toList());
        return "product";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editProduct(@PathVariable(required = false) Long id,
                              Model model, Principal principal) {
        String roleName = ((Authentication)principal).getAuthorities().toArray()[0].toString();
        if(UserRole.ADMIN.toString().equals(roleName)) {
            if (id == null || id <= 0) {
                model.addAttribute("productDto", new ProductDto());
            } else {
                model.addAttribute("productId", id);
                model.addAttribute("productDto", new ProductDto(productService.findProduct(id)));
            }
            return "product-edit";
        }
        else return "redirect:/product";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveProduct(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid ProductDto productDto,
                              BindingResult bindingResult,
                              Model model,
                              Principal principal) {
        String roleName = ((Authentication)principal).getAuthorities().toArray()[0].toString();
        if(UserRole.ADMIN.toString().equals(roleName)) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "product-edit";
            }
            if (id == null || id <= 0) {
                productService.addProduct(productDto.getName(), productDto.getCost());
            } else {
                productService.updateProduct(id, productDto.getName(), productDto.getCost());
            }
            return "redirect:/product";
        }
        else return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        String roleName = ((Authentication)principal).getAuthorities().toArray()[0].toString();
        if(UserRole.ADMIN.toString().equals(roleName)) {
            productService.deleteProduct(id);
            return "redirect:/product";
        }
        else return "redirect:/product";
    }
}
