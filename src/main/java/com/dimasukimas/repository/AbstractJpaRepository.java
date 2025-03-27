package com.dimasukimas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbstractJpaRepository<T, ID> implements Repository<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<T> entityType;

    @Override
    public void persist(T t) {
        entityManager.persist(t);
    }
}
