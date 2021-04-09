package com.customerfarm.springboot.services;

import com.customerfarm.springboot.exceptions.FarmNotFoundException;
import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.repositories.FarmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("farmService")
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepo farmRepo;


    @Override
    public List<Farm> findAll() {
        return farmRepo.findAll();
    }



    @Override
    public Farm findFarmById(long id) {

        Farm farm = farmRepo.findById(id).orElseThrow(() -> new FarmNotFoundException("Farm not found with id: " + id));

        return farm;
    }


    @Override
    public Farm findFarmByName(String name) {

        Farm farm = farmRepo.findByName(name).orElseThrow(() -> new FarmNotFoundException("Farm not found with name: " + name));
        return farm;

    }


    @Override
    @Transactional
    public Farm save(Farm farm) {
        return farmRepo.save(farm);
    }


    @Override
    @Transactional
    public Farm delete(Farm farm) {

        farmRepo.delete(farm);
        return farm;
    }
}
