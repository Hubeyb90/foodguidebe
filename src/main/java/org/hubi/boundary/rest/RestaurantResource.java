package org.hubi.boundary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.hubi.control.RestaurantService;
import org.hubi.entity.Restaurant;

public class RestaurantResource implements RestaurantApi {

    @Inject
    RestaurantService restaurantService;

    @Override
    public Response getRestaurants() {
        return Response.ok(restaurantService.getRestaurants()).build();
    }

    @Override
    public Response getRestaurantById(Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(restaurantService.getRestaurantById(id)).build();
        }
    }

    @Override
    public Response addRestaurant(Restaurant restaurant) {
        return Response.ok(restaurantService.addRestaurant(restaurant)).build();
    }

    @Override
    public Response updateRestaurant(Restaurant restaurant) {
        return Response.ok(restaurantService.updateRestaurant(restaurant)).build();
    }

    @Override
    public void deleteById(Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id %s was not found.", id));
        } else {
            restaurantService.deleteById(id);
        }
    }
}
