package com.teju.finance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.teju.finance.entity.User;
import com.teju.finance.exception.UnauthorizedException;
import com.teju.finance.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(
            @RequestHeader("role") String role,
            @Valid @RequestBody User user) {

        if (!role.equals("ADMIN")) {
        	throw new UnauthorizedException("Access Denied: Only ADMIN can create users");
        }

        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    
    
}
