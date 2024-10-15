package org.hubi

import org.hubi.entity.Restaurant

class TestFactory {

    static Restaurant createRestaurant(int id = 1, String name) {
        return new Restaurant(name)
    }
}
