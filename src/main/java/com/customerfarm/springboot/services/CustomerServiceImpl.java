package com.customerfarm.springboot.services;

import com.customerfarm.springboot.exceptions.AccountNotFoundException;
import com.customerfarm.springboot.exceptions.CustomerNotFoundException;
import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer findById(long id) {

        Customer customer = customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        return customer;
    }

    @Override
    public Account findAccountById(long id) {

        Account account = customerRepo.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("Account not found for the customer id: " + id));

        return account;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    @Transactional
    public Customer delete(Customer customer) {

        customerRepo.delete(customer);
        return customer;
    }
}
