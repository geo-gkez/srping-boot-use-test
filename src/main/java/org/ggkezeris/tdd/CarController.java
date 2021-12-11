package org.ggkezeris.tdd;

import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{name}")
    private Car getCar(@PathVariable String name){
        Car car = this.carService.getCarDetails(name);
        if (car == null) {
            throw new CarNotFoundException();
        }
        return car;
    }


}
