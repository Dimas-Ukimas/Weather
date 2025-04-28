package com.dimasukimas.repository;

import com.dimasukimas.entity.User;
import com.dimasukimas.exception.UserAlreadyExistsException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends AbstractJpaRepository<User, Integer> {

    protected UserRepository() {
        super(User.class);
    }

    public Optional<User> findUserByLogin(String login){

        String query = "SELECT u from User u WHERE u.login=:login";

        List<User> user = entityManager.createQuery(query, User.class)
                .setParameter("login", login)
                .getResultList();

      return user.isEmpty() ? Optional.empty() : Optional.of(user.get(0));
    }

    @Override
    public void persist(User user) {
        try {
        entityManager.persist(user);}
        catch (ConstraintViolationException e){
            throw new UserAlreadyExistsException("This username is already taken");
            }
    }

}
