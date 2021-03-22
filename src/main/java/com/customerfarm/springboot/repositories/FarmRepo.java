package com.customerfarm.springboot.repositories;

import com.customerfarm.springboot.model.Account;
import com.customerfarm.springboot.model.Farm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepo extends CrudRepository<Farm, Long> {

    List<Farm> findAll();
    @Query("SELECT f.account FROM Farm f WHERE f.id = :id")
    Optional<Account> findAccountById(long id);
    Optional<Farm> findFarmByName(String name);
}
