package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Customer;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.model.User;
import com.customerfarm.springboot.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void checkUsersCountFromRepo(){

        assertEquals(3, userRepo.count());

    }


    @Test
    public void shouldReturnUserWhenFindById(){

        Long userId = 1L;
        String username = "mmarkovic";
        String encodedPassword = "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG";
        boolean enabled = true;

        Optional<User> userFound = userRepo.findById(userId);
        assertThat(userFound).isNotEmpty();
        assertThat(userFound).get().hasFieldOrPropertyWithValue("id", userId).hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", encodedPassword).hasFieldOrPropertyWithValue("enabled", enabled);

    }


    @Test
    public void shouldReturnUserWhenFindByUsername(){

        String username = "bmaric";

        Optional<User> userFound = userRepo.findByUsername(username);

        assertThat(userFound).isNotEmpty().get().hasFieldOrPropertyWithValue("username", username);

    }

    @Test
    public void shouldReturnUserWhenFindByUsernameAndPassword(){

        String username = "sasam0320";
        String encodedPassword = "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG";

        Optional<User> userFound = userRepo.findByUsernameAndPassword(username, encodedPassword);

        assertThat(userFound).isNotEmpty().get().hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", encodedPassword)
                .hasFieldOrPropertyWithValue("enabled", true);

    }

    @Test
    public void shouldReturnAccountListWhenFindAccountsById(){

      Long userId = 1L;
        String username = "mmarkovic";
        String encodedPassword = "$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG'";
        boolean enabled = true;

        User userInfo = new User(username, encodedPassword, enabled);

        Farm farm1 = new Farm();
        farm1.setId(1L);
        farm1.setName("Markos Walleye");
        farm1.setPrice(30500.25);

        Farm farm2 = new Farm();
        farm2.setId(1L);
        farm2.setName("Williams Ranch");
        farm2.setPrice(44500.00);

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Marko");
        customer1.setLastName("Markovic");
        customer1.setEmail("marko.markovic@gmail.com");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("William");
        customer2.setLastName("Petters");
        customer2.setEmail("william.petters@gmail.com");

        Account account1 = new Account();
        account1.setId(1L);
        account1.setUserInfo(userInfo);
        account1.setFarm(farm1);
        account1.setCustomer(customer1);

        Account account2 = new Account();
        account1.setId(2L);
        account1.setUserInfo(userInfo);
        account1.setFarm(farm2);
        account1.setCustomer(customer2);

        Account account3 = new Account();
        account1.setId(3L);
        account1.setUserInfo(userInfo);

        List<Account> userAccounts =userRepo.findAccountsById(userId) ;

        assertThat(userAccounts).isNotEmpty().hasSize(3);

    }
}
