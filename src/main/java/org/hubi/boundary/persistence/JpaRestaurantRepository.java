package org.hubi.boundary.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hubi.entity.Restaurant;
import org.hubi.entity.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Restaurant> findAll() {
        return entityManager.createQuery("FROM Restaurant", Restaurant.class).getResultList() ;
    }

    @Override
    public Restaurant findById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
       entityManager.persist(restaurant);
       return restaurant;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        entityManager.remove(restaurant);
    }
}
