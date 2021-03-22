package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.dto.UserDTO;
import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;




@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
    
	@GetMapping
	public List<User> getAllUsers() {

		return userService.findAll();
	}
	
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		
		User user = userService.findById(id);
		
		return user;
	}

    @GetMapping("/search")
    public User getUserByName(@RequestParam(value = "name") String name) {

        User user = userService.findByUsername(name);

        return user;

    }

    @GetMapping("/{id}/account")
    public List<Account> getAccountById(@PathVariable long id) {

        List<Account> accounts = userService.findAccountsById(id);

        return accounts;

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody UserDTO userDto) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.isEnabled());

        return userService.save(user);

    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable("id") long userId, @RequestBody UserDTO userDto) {

        User user = userService.findById(userId);
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.isEnabled());

        return userService.save(user);

    }

    @DeleteMapping(path = "/{id}")
    public User deleteUser(@PathVariable("id") long userId){

        User user = userService.findById(userId);
        userService.delete(user);

        return user;
    }

}
