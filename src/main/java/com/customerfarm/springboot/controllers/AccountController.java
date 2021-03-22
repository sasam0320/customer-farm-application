package com.customerfarm.springboot.controllers;


import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {

        return accountService.findAll();
    }


    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable long id) {

        Account account = accountService.findById(id);

        return account;
    }


    @GetMapping("/{id}/farm")
    public List<Farm> getUserFarmsById(@PathVariable long id) {

        List<Farm> farms = accountService.findFarmByUser(id);

        return farms;

    }


    @GetMapping("/{id}/customer")
    public Customer getCustomerById(@PathVariable long id) {

        Customer customer = accountService.findCustomerById(id);

        return customer;

    }


}
