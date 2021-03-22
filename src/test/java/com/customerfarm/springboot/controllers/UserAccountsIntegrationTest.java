package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.CustomerFarmAppApplication;
import com.customerfarm.springboot.config.SecurityConfigTest;
import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.services.AccountServiceImpl;
import com.customerfarm.springboot.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes={SecurityConfigTest.class, UserController.class, AccountController.class, CustomerFarmAppApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserAccountsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private AccountServiceImpl accountService;

    private List<User> users;

    private Account account;

    private User userInfo;

    private Customer customer;

    private List<Farm> farms;

    @BeforeEach
    void setUp() {

        this.users = new ArrayList<>();
        this.users.add(new User(1,"mmarkovic", "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG", true));
        this.users.add(new User(2,"bmaric", "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG", true));
        this.users.add(new User(3, "sasam0320", "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG", true));

        String username = "bmaric";
        String encodedPassword = "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG'";
        boolean enabled = true;

        this.userInfo = new User(2, username, encodedPassword, enabled);

        Farm farm1 = new Farm();
        farm1.setId(3L);
        farm1.setName("Magic Walleye");
        farm1.setPrice(52500.5);

        Farm farm2 = new Farm();
        farm2.setId(4L);
        farm2.setName("Magic Walleye");
        farm2.setPrice(60200.00);

        this.farms = Arrays.asList(farm1, farm2);

        Customer customer = new Customer();
        customer.setId(3L);
        customer.setFirstName("Branko");
        customer.setLastName("Maric");
        customer.setEmail("branko.maric@gmail.com");

        this.customer = customer;

        Account account = new Account();
        account.setId(4L);
        account.setUserInfo(userInfo);
        account.setFarm(farm1);
        account.setCustomer(customer);

        this.account = account;


    }

    // UserController test methods

    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnAllUsers() throws Exception {

        User sampleUser = this.users.get(0);

        given(userService.findAll()).willReturn(this.users);
        this.mockMvc.perform(get("/users").secure(true))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].username").value(sampleUser.getUsername()));
    }

    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnOneUserById() throws Exception {

        User sampleUser = this.users.get(0);
        long userId = sampleUser.getId();

        given(userService.findById(userId)).willReturn(sampleUser);
        this.mockMvc.perform(get("/users/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnOneUserByUsername() throws Exception {

        User sampleUser = this.users.get(1);

        given(userService.findByUsername(sampleUser.getUsername())).willReturn(sampleUser);
        this.mockMvc.perform(get("/users/search")
                .param("name", sampleUser.getUsername()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnAccountListById() throws Exception {

        User sampleUser = this.users.get(2);
        long userId = sampleUser.getId();

        Customer customer = new Customer();
        customer.setId(5L);
        customer.setFirstName("Sasa");
        customer.setLastName("Milenkovic");
        customer.setEmail("sasam0320@gmail.com");

        Farm farm = new Farm();
        farm.setId(6L);
        farm.setName("Mountain Shadow Fields");
        farm.setPrice(50000.00);


        Account account = new Account();
        account.setId(3L);
        account.setUserInfo(sampleUser);
        account.setFarm(farm);
        account.setCustomer(customer);

        List<Account> accounts = Arrays.asList(account);

        given(userService.findAccountsById(userId)).willReturn(accounts);
        this.mockMvc.perform(get("/users/"+userId+"/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

     // Account controller test methods

    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnAccountById() throws Exception {

        long accountId = this.account.getId();

        given(accountService.findById(accountId)).willReturn(this.account);
        this.mockMvc.perform(get("/accounts/" + accountId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnFarmByUser() throws Exception {

        long accountId = this.account.getId();

        given(accountService.findFarmByUser(accountId)).willReturn(this.farms);
        this.mockMvc.perform(get("/accounts/" + accountId + "/farm"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }


    @WithMockUser("sasam0320")
    @Test
    public void shouldReturnCustomerById() throws Exception {

        long accountId = this.account.getId();

        given(accountService.findCustomerById(accountId)).willReturn(this.customer);
        this.mockMvc.perform(get("/accounts/" + accountId + "/customer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }



}
