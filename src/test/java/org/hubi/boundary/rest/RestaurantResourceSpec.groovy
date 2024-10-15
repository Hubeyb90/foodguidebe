package org.hubi.boundary.rest

import jakarta.ws.rs.NotFoundException
import org.hubi.control.RestaurantService
import org.hubi.entity.Restaurant
import spock.lang.Specification

import static org.hubi.TestFactory.createRestaurant

class RestaurantResourceSpec extends Specification {

    def subject = new RestaurantResource(restaurantService: Mock(RestaurantService))

    def "getRestaurants"() {
        given:
        def restaurant1 = createRestaurant(1, "restaurant 1")
        def restaurant2 = createRestaurant(2, "restaurant 2")
        subject.restaurantService.getRestaurants() >> List.of(restaurant1, restaurant2)

        when:
        def result = subject.getRestaurants()

        then:
        result.readEntity(List<Restaurant>).size() == 2
        result.readEntity(List<Restaurant>).getFirst() == restaurant1
        result.readEntity(List<Restaurant>).getLast() == restaurant2
    }

    def "getRestaurantById"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantService.getRestaurantById(restaurant.getId()) >> restaurant

        when:
        def result = subject.getRestaurantById(restaurant.getId())

        then:
        result.readEntity(Restaurant) == restaurant
    }

    def "addRestaurant"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantService.addRestaurant(restaurant) >> restaurant

        when:
        def result = subject.addRestaurant(restaurant)

        then:
        result.readEntity(Restaurant) == restaurant
    }

    def "updateRestaurant"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantService.updateRestaurant(restaurant) >> restaurant

        when:
        def result = subject.updateRestaurant(restaurant)

        then:
        result.readEntity(Restaurant) == restaurant
    }

    def "deleteById"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantService.getRestaurantById(restaurant.getId()) >> restaurant

        when:
        subject.deleteById(restaurant.getId())

        then:
        1 * subject.restaurantService.deleteById(restaurant.getId())
    }

    def "deleteById wirft Fehler wenn Id nicht gefunden"() {
        given:
        subject.restaurantService.getRestaurantById(1) >> null

        when:
        subject.deleteById(1)

        then:
        0 * subject.restaurantService.deleteById(1)
        thrown(NotFoundException)
    }
}
