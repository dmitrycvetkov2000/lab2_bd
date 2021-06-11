package com.example.repository;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface Repository<T> {
    T get(Session session, Long id);
    int count(Session session);
    List<T> findAll(Session session);
    T update(Session session, T entity);
    T save(Session session, T entity);
    void delete(Session session, T entity);
    default void delete(Session session, Serializable id){
        session.delete(get(session, (Long) id));
    }
}
