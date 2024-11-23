package com.mailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import com.mailservice.entities.UserEntityCl;
import com.mailservice.service.UserMailService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
@Validated
public class UserMailController {

    @Autowired
    private UserMailService userMailService;

    @PostMapping("/create")
    public UserEntityCl createUser(@Valid @RequestBody UserEntityCl userEntityCl) {
        return userMailService.createUser(userEntityCl);
    }

    @PutMapping("/update/{id}")
    public UserEntityCl updateUser(@PathVariable Long id, @Valid @RequestBody UserEntityCl userEntityCl) {
        return userMailService.updateUser(id, userEntityCl);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userMailService.deleteUser(id);
        return "User with id :"+id + ": has been deleted!!";
    }
}
