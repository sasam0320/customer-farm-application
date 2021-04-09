package com.customerfarm.springboot.repositories;

import java.util.List;
import java.util.Optional;

import com.customerfarm.springboot.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customerfarm.springboot.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	
	List<User> findAll();

	Optional<User> findById(long id);

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameAndPassword(String user, String password);

	@Query("SELECT u.accounts FROM User u WHERE u.id = :id")
	List<Account> findAccountsById(long id);


}
