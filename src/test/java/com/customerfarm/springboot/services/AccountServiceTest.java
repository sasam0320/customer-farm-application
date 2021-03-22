package com.customerfarm.springboot.services;


import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.repositories.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepo accountRepo;

    private Account account;

    private User userInfo;

    private Customer customer;

    @BeforeEach
    void setUp() {

        String username = "mmarkovic";
        String encodedPassword = "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG'";
        boolean enabled = true;

        this.userInfo = new User(username, encodedPassword, enabled);

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
    public void shouldReturnAccountWhenFindById(){

         Mockito.lenient().when(accountRepo.findById(this.account.getId())).thenReturn(Optional.ofNullable(this.account));
         assertEquals(this.account, accountRepo.findById(this.account.getId()).get());
    }

    @Test
    public void shouldReturnUserWhenFindUserInfoById(){

        Mockito.lenient().when(accountRepo.findUserInfoById(this.account.getId())).thenReturn(Optional.ofNullable(this.userInfo));
        assertEquals(this.userInfo, accountRepo.findUserInfoById(this.account.getId()).get());
    }

    @Test
    public void shouldReturnCustomerWhenFindCustomerById(){

        Mockito.lenient().when(accountRepo.findCustomerById(this.account.getId())).thenReturn(Optional.ofNullable(this.customer));
        assertEquals(this.customer, accountRepo.findCustomerById(this.account.getId()).get());

    }
}
