package com.dimasukimas.repository;

public interface Repository<T, ID>{

 void persist(T t);

}
