package com.dimasukimas.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, ID>{

 void persist(E entity);

 Optional<E> findById(ID id);
//
// void delete(ID id);
//
// E update(E entity);
//
// List<E> findAllByField(String fieldName, V value);

}
