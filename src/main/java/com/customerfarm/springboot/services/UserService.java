package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User findById(long id);

    User findByUsername(String name);

    User findByUsernameAndPassword(String username, String password);

    List<Account> findAccountsById(long id);

    User save (User user);

    User delete(User user);

}
