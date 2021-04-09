package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.services.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarms() {

        return farmService.findAll();
    }

    @GetMapping("/{id}")
    public Farm getFarmById(@PathVariable("id") long id){

        return farmService.findFarmById(id);
    }

    @GetMapping("/search")
    public Farm getFarmByName(@RequestParam(value = "name") String name) {

        Farm farm = farmService.findFarmByName(name);

        return farm;
    }
}
