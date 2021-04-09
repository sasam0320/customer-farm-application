package com.customerfarm.springboot.services;

import com.customerfarm.springboot.exceptions.AccountNotFoundException;
import com.customerfarm.springboot.exceptions.UserNotFoundException;
import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(long id) {

        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return user;
    }

    @Override
    public User findByUsername(String username) {

        User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with name: " + username));
        return user;

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        User user = userRepo.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UserNotFoundException("User not found with username : " + username + " and password: " + password));
        return user;
    }

    @Override
    public List<Account> findAccountsById(long id) {

        List<Account> accounts = userRepo.findAccountsById(id);

        if (accounts == null || accounts.isEmpty()) {

            throw new AccountNotFoundException("Account not found with user id : " + id);
        }

        return accounts;
    }

    @Override
    @Transactional
    public User save(User user) {

        return userRepo.save(user);

    }


    @Override
    @Transactional
    public User delete(User user) {

        userRepo.delete(user);

        return user;

    }
}
