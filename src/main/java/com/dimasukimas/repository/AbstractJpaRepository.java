package com.dimasukimas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AbstractJpaRepository<E, ID> implements Repository<E, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<E> entityType;

    @Override
    public void persist(E e) {
        entityManager.persist(e);
    }

//    @Override
//    public void find(T t) {
//        entityManager.find();
//    }

}
