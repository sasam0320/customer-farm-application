package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<Customer> getAllCustomers() {

		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable long id) {

		Customer customer = customerService.findById(id);

		return customer;
	}

	@GetMapping("/{id}/account")
	public Account getAccountById(@PathVariable long id) {

		Account account = customerService.findAccountById(id);

		return account;
	}


}
