package org.ggkezeris.tdd;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepo carRepo;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @Cacheable("cars")
    public Car getCarDetails(String name) {
        Car car = carRepo.findByName(name);
        if(car==null){
            throw new CarNotFoundException();
        }
        return car;
    }
}
