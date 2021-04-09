package com.customerfarm.springboot.repositories;

import com.customerfarm.springboot.model.Farm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepo extends CrudRepository<Farm, Long> {

    List<Farm> findAll();
    Optional<Farm> findById(long id);
    Optional<Farm> findByName(String name);
}
