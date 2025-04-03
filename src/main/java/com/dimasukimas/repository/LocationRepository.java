package com.dimasukimas.repository;

import com.dimasukimas.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository extends AbstractJpaRepository<Location, Integer> {

    public LocationRepository(){
        super(Location.class);
    }
}
