package org.ggkezeris.tdd;

import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<Car,Long> {
     Car findByName(String name);
}
