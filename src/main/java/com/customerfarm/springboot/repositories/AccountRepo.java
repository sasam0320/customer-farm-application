package com.customerfarm.springboot.repositories;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    List<Account> findAll();
    Optional<Account> findById(long id);
    @Query("SELECT a.userInfo FROM Account a WHERE a.id = :id")
    Optional<User> findUserInfoById(long id);
    @Query("SELECT a.customer FROM Account a WHERE a.id = :id")
    Optional<Customer> findCustomerById(long id);

}
