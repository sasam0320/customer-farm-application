package com.customerfarm.springboot.controllers;


import com.customerfarm.springboot.CustomerFarmAppApplication;
import com.customerfarm.springboot.config.SecurityConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {SecurityConfigTest.class, UserController.class, AccountController.class,
        CustomerController.class, FarmController.class, CustomerFarmAppApplication.class})
@AutoConfigureMockMvc
class ExceptionControllerTest {

    private final String DELIMITTER = "/";
    private String baseUrl = "http://localhost:8080";
    @Value("${server.servlet.context-path}")
    private String contextPath;
    private String endpointToUserException = "/users/search";
    private String endpointToAccountException = "/accounts";
    private String endpointToCustomerException = "/customers";
    private String endpointToFarmException = "/farms/search";
    private String targetUrl;
    @Autowired
    private MockMvc mvc;

    @WithMockUser("sasam0320")
    @Test
    public void testHandleUserNotFoundException() throws Exception {

        final String name = "customer2021";
        targetUrl = baseUrl + contextPath + endpointToUserException;
        this.mvc.perform(get(targetUrl).param("name", name))
                .andExpect(status().isNotFound());
/*               .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                 .andExpect(result -> assertEquals("User not found with name: " + name,
                            result.getResolvedException().getMessage()));*/

    }

    @WithMockUser("sasam0320")
    @Test
    public void testHandleAccountNotFoundException() throws Exception {

        targetUrl = baseUrl + contextPath + endpointToAccountException;
        final long accountId = 7;
        this.mvc.perform(get(targetUrl + DELIMITTER + accountId))
                .andExpect(status().isNotFound());
/*              .andExpect(result -> assertTrue(result.getResolvedException() instanceof AccountNotFoundException))
                .andExpect(result -> assertEquals("Account not found with id: " + accountId,
                        result.getResolvedException().getMessage()));*/
    }

    @WithMockUser("sasam0320")
    @Test
    public void testHandleCustomerNotFoundException() throws Exception {

        targetUrl = baseUrl + contextPath + endpointToCustomerException;
        final long customerId = 6;
        this.mvc.perform(get(targetUrl + DELIMITTER + customerId))
                .andExpect(status().isNotFound());
    }

    @WithMockUser("sasam0320")
    @Test
    public void testHandleFarmNotFoundException() throws Exception {

        targetUrl = baseUrl + contextPath + endpointToFarmException;

        final String name = "Welcome to paradise";

        this.mvc.perform(get(targetUrl).param("name", name))
                .andExpect(status().isNotFound());
    }

}
