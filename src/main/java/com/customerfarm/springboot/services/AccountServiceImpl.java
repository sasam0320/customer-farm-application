package com.customerfarm.springboot.services;

import com.customerfarm.springboot.exceptions.AccountNotFoundException;
import com.customerfarm.springboot.exceptions.CustomerNotFoundException;
import com.customerfarm.springboot.exceptions.UserNotFoundException;
import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;


    @Override
    public List<Account> findAll() {

        return accountRepo.findAll();

    }

    @Override
    public Account findById(long id) {

        Account account = accountRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

        return account;
    }


    @Override
    public List<Farm> findFarmByUser(long id) {

        User user = accountRepo.findUserInfoById(id).orElseThrow(() -> new UserNotFoundException("User info not found for the account id: " + id));

        List<Account> accounts = user.getAccounts();

        List<Farm> farms = new ArrayList<>(accounts.size());

        accounts.forEach(account -> farms.add(account.getFarm()));

        return farms;
    }

    @Override
    public Customer findCustomerById(long id) {

        Customer customer = accountRepo.findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found for account id : " + id));

        return customer;
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    @Transactional
    public Account delete(Account account) {

        accountRepo.delete(account);
        return account;
    }
}
