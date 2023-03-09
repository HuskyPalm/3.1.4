package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping
public class MainPageController {
    private final UserService userService;

    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showMain(Principal principal) {
        if (principal != null) {
            Set<Role> roles = userService.getPersonByUsername(principal.getName()).getRoles();
            if (roles.contains(Role.Admin())) {
                return "redirect:/admin";
            } else if (roles.contains(Role.User())) {
                return "redirect:/user";
            }
        }
        return "/login";
    }
}