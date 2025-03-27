package com.dimasukimas.repository;

import com.dimasukimas.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractJpaRepository<User, Integer> {

    protected UserRepository() {
        super(User.class);
    }

}
