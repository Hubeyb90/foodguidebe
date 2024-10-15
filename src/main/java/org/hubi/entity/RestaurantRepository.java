package org.hubi.entity;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(Long id);

}
