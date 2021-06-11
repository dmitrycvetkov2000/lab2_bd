package com.example.repository;

import com.example.model.Client;
import com.example.model.Product;
import org.hibernate.Session;

import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

public class ProductRepository implements Repository<Product> {
    public ProductRepository() {

    }

    @Override
    public Product get(Session session, Long id) {
        return session.get(Product.class, id);
    }

    @Override
    public int count(Session session) {
        return ((BigInteger) session.createSQLQuery("SELECT COUNT(id) FROM "  + Product.class.getAnnotation(Table.class).name()).uniqueResult()).intValue();
    }

    @Override
    public List<Product> findAll(Session session) {
        return session.createCriteria(Product.class).list();
    }

    @Override
    public Product update(Session session, Product entity) {
        if (entity.getId() == null)
            throw new RuntimeException("Id cannot be null in update method");
        session.update(entity);
        return entity;
    }

    @Override
    public Product save(Session session, Product user) {
        Long id = (Long) session.save(user);
        user.setId(id);
        return user;
    }

    @Override
    public void delete(Session session, Product entity) {
        session.delete(entity);
    }
}
