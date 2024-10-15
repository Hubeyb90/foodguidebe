package org.hubi.control


import org.hubi.entity.RestaurantRepository
import spock.lang.Specification

import static org.hubi.TestFactory.createRestaurant

class RestaurantServiceSpec extends Specification {

    def subject = new RestaurantService(
            restaurantRepository: Mock(RestaurantRepository)
    )

    def "getRestaurants"() {
        given:
        def restaurant1 = createRestaurant(1, "restaurant 1")
        def restaurant2 = createRestaurant(2, "restaurant 2")
        subject.restaurantRepository.findAll() >> List.of(restaurant1, restaurant2)

        when:
        def result = subject.getRestaurants()

        then:
        result.size() == 2
        result.getFirst() == restaurant1
        result.getLast() == restaurant2
    }

    def "getRestaurantById"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantRepository.findById(restaurant.getId()) >> restaurant

        when:
        def result = subject.getRestaurantById(restaurant.getId())

        then:
        result == restaurant
    }

    def "addRestaurant"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantRepository.save(restaurant) >> restaurant

        when:
        def result = subject.addRestaurant(restaurant)

        then:
        result == restaurant
    }

    def "updateRestaurant"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantRepository.update(restaurant) >> restaurant

        when:
        def result = subject.updateRestaurant(restaurant)

        then:
        result == restaurant
    }

    def "deleteById"() {
        given:
        def restaurant = createRestaurant(1, "restaurant")
        subject.restaurantRepository.findById(restaurant.getId()) >> restaurant

        when:
        subject.deleteById(restaurant.getId())

        then:
        1 * subject.restaurantRepository.delete(restaurant.getId())
    }
}
