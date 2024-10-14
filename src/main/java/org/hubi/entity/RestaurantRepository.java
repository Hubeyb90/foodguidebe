package org.hubi.entity;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Long id);

}
