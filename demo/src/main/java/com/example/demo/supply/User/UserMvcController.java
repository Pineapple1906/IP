package com.example.demo.supply.User;

import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UserMvcController {
    private final UserService userService;

    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int size,
                           Model model, Principal principal) {

        String roleName = ((Authentication)principal).getAuthorities().toArray()[0].toString();
        if(UserRole.ADMIN.toString().equals(roleName)) {
            final Page<UserDto> users = userService.findAllPages(page, size)
                    .map(UserDto::new);
            model.addAttribute("users", users);
            final int totalPages = users.getTotalPages();
            final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pages", pageNumbers);
            model.addAttribute("totalPages", totalPages);
            return "users";
        }
        else{
            model.addAttribute("error", "Доступ запрещен");
            return "error";
        }
    }
}
