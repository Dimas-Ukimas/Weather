package com.dimasukimas.repository;

import java.util.Optional;

public interface Repository<E, ID>{

 void persist(E entity);

 Optional<E> findById(ID id);

 void delete(E entity);

}
