package com.customerfarm.springboot.services;


import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.repositories.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {

    @Autowired
    private AccountRepo accountRepo;

    private Account account;

    private User userInfo;

    private Customer customer;

    @BeforeEach
    void setUp() {

        String username = "mmarkovic";
        String encodedPassword = "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG'";
        boolean enabled = true;

        this.userInfo = new User(1L, username, encodedPassword, enabled);

        Farm farm = new Farm();
        farm.setId(1L);
        farm.setName("Markos Walleye");
        farm.setPrice(30500.25);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Marko");
        customer.setLastName("Markovic");
        customer.setEmail("marko.markovic@gmail.com");

        this.customer = customer;

        Account account = new Account();
        account.setId(1L);
        account.setUserInfo(userInfo);
        account.setFarm(farm);
        account.setCustomer(customer);

        this.account = account;

    }

    @Test
    public void shouldReturnAllAccountsWhenFindAll(){

        List<Account> accounts = accountRepo.findAll();

        int numOfAccounts = 6;

        assertThat(accounts).hasSize(numOfAccounts);

    }

    @Test
    public void shouldReturnAccountWhenFindById(){

        Optional<Account> accountFound = accountRepo.findById(this.account.getId());

        assertThat(accountFound).isNotEmpty().get().hasFieldOrProperty("userInfo")
                .hasFieldOrProperty("customer");

    }

    @Test
    public void shouldReturnUserWhenFindUserInfoById(){

        Optional<User> userFound = accountRepo.findUserInfoById(this.account.getId());

         assertThat(userFound.get()).isNotNull().hasFieldOrPropertyWithValue("id", this.userInfo.getId());
    }

    @Test
    public void shouldReturnCustomerWhenFindCustomerById(){

        Optional<Customer> customerFound = accountRepo.findCustomerById(this.customer.getId());

        assertThat(customerFound.get()).isNotNull().hasFieldOrPropertyWithValue("firstName", this.customer.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", this.customer.getLastName())
                .hasFieldOrPropertyWithValue("email", this.customer.getEmail());

    }
}
