package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.services.AuthUser;
import com.customerfarm.springboot.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping
    public AuthUser authenticateUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){

        User user = userService.findByUsernameAndPassword(username, password);

        log.info("User is authenticated successfully.");
        return new AuthUser(user, "User is authenticated successfully.");
    }
}
