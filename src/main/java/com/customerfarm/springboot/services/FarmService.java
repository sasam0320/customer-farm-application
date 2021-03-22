package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.Farm;

import java.util.List;

public interface FarmService {

    List<Farm> findAll();

    Farm findFarmByName(String name);

    Farm save(Farm farm);

    Farm delete(Farm farm);
}
