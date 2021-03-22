package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username){

        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user with username: " + username));

        return new AuthUser(user);
    }
}
