package com.dimasukimas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AbstractJpaRepository<E, ID> implements Repository<E, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<E> entityClass;

    @Override
    public void persist(E e) {
        entityManager.persist(e);
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

//    @Override
//    public List<E> findAllByField(String fieldName, V value) {
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
//        Root<E> root = criteriaQuery.from(entityClass);
//        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(fieldName), value));
//
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }

}
