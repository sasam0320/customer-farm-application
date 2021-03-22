package com.customerfarm.springboot.repositories;

import java.util.List;
import java.util.Optional;

import com.customerfarm.springboot.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customerfarm.springboot.model.Customer;


@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {
	
	List<Customer> findAll();
	Optional<Customer> findById(long id);
	@Query("SELECT c.account FROM Customer c WHERE c.id = :id")
	Optional<Account> findAccountById(long id);

}
