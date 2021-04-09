package com.customerfarm.springboot.services;

import com.customerfarm.springboot.model.Farm;
import com.customerfarm.springboot.repositories.FarmRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FarmServiceTest {

    @Autowired
    private FarmRepo farmRepo;

    private List<Farm> farms;

    final int LIST_INDEX = 2;

    @BeforeEach
    void setUp(){

        Farm farm1 = new Farm();
        farm1.setId(1L);
        farm1.setName("Markos Walleye");
        farm1.setPrice(30500.25);

        Farm farm2 = new Farm();
        farm2.setId(2L);
        farm2.setName("Williams Ranch");
        farm2.setPrice(44500.00);

        Farm farm3 = new Farm();
        farm3.setId(3L);
        farm3.setName("Magic Walleye");
        farm3.setPrice(52500.5);

        Farm farm4 = new Farm();
        farm4.setId(4L);
        farm4.setName("Green Haven");
        farm4.setPrice(60200.00);

        this.farms = Arrays.asList(farm1, farm2, farm3, farm4);

    }

    @Test
    public void shouldNotReturnAllFarmsWhenFindAll(){

        assertNotEquals(this.farms, farmRepo.findAll());
    }

    @Test
    public void shouldReturnFarmWhenFindById(){

        long farmId = 1L;

        Optional<Farm> farmFound = farmRepo.findById(farmId);
        assertThat(farmFound).isNotEmpty().get().hasFieldOrPropertyWithValue("id", farmId);
    }

    @Test
    public void shouldReturnFarmWhenFindByName(){

        String name = this.farms.get(LIST_INDEX).getName();
        Optional<Farm> farmFound = farmRepo.findByName(name);

        assertThat(farmFound).isNotEmpty();
        assertThat(farmFound.get()).hasFieldOrPropertyWithValue("name", name);
    }

}
