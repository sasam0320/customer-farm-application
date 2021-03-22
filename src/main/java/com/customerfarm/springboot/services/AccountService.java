package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long id);

    List<Farm> findFarmByUser(long id);

    Customer findCustomerById(long id);

    Account save(Account account);

    Account delete(Account account);
}
