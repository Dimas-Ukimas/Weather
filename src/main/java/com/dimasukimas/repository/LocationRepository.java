package com.dimasukimas.repository;

import com.dimasukimas.entity.Location;
import com.dimasukimas.entity.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository extends AbstractJpaRepository<Location, Integer> {

    public LocationRepository() {
        super(Location.class);
    }

    public List<Location> findAllByUser(User user) {

        String query = "SELECT l FROM Location l WHERE l.user =:user";

        return entityManager.createQuery(query, Location.class)
                .setParameter("user", user)
                .getResultList();
    }

    public Optional<Location> findByCoordAndUser(BigDecimal longitude, BigDecimal latitude, User user){
        String query = "SELECT l FROM Location l WHERE l.user =:user AND l.longitude =:longitude AND l.latitude =:latitude";

        return Optional.ofNullable(entityManager.createQuery(query, Location.class)
                .setParameter("user", user)
                .setParameter("longitude", longitude)
                .setParameter("latitude", latitude)
                .getSingleResult());
    }

}
