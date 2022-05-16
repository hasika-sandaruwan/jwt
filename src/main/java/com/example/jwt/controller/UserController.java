package com.example.jwt.controller;

import com.example.jwt.entity.User;
import com.example.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUsers() {
        userService.initRolesAndUsers();
    }

    @PostMapping({"/createNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This Url is only accessible to admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String forUser() {
        return "This Url is only accessible to the user";
    }

}
