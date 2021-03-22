package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import java.util.List;


public interface CustomerService {

    List<Customer> findAll();

    Customer findById(long id);

    Account findAccountById(long id);

    Customer save(Customer customer);

    Customer delete(Customer customer);
}
