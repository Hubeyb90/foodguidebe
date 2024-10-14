package org.hubi.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hubi.entity.Restaurant;
import org.hubi.entity.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RestaurantService {

    @Inject
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteById(Long id) {
        restaurantRepository.delete(id);
    }
}
