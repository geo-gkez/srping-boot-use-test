package org.ggkezeris.tdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarRepoTest {


    @Autowired
    private CarRepo repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void getCar_returnsCarDetails() {

        Car savedCar = entityManager.persistFlushFind(new Car("yaris","hybrid"));

        Car car = repo.findByName("yaris");

        Assertions.assertThat(car.getName()).isEqualTo(savedCar.getName());
        Assertions.assertThat(car.getType()).isEqualTo(savedCar.getType());
    }
}