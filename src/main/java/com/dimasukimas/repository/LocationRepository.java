package com.dimasukimas.repository;

import com.dimasukimas.entity.Location;
import com.dimasukimas.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository extends AbstractJpaRepository<Location, User> {

    public LocationRepository() {
        super(Location.class);
    }

    public List<Location> findAllByUser(User user) {

        String query = "SELECT l FROM Location l WHERE l.user =:user";

        return entityManager.createQuery(query, Location.class)
                .setParameter("user", user)
                .getResultList();
    }

}
