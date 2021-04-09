package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.dto.UserDTO;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.services.AuthUser;
import com.customerfarm.springboot.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping
    public AuthUser authenticateUser(@RequestBody UserDTO userDto){

        String username = userDto.getUsername();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(userDto.getPassword());

        User user = userService.findByUsernameAndPassword(username, encodedPassword);

        log.info("User is authenticated successfully.");
        return new AuthUser(user, "User is authenticated successfully.");
    }
}
