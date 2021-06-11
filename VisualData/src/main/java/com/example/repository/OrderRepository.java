package com.example.repository;

import com.example.model.Order;
import com.example.model.Product;
import org.hibernate.Session;

import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

public class OrderRepository implements Repository<Order> {
    public OrderRepository() {

    }

    @Override
    public Order get(Session session, Long id) {
        return session.get(Order.class, id);
    }

    @Override
    public int count(Session session) {
        return ((BigInteger) session.createSQLQuery("SELECT COUNT(id) FROM "  + Order.class.getAnnotation(Table.class).name()).uniqueResult()).intValue();
    }

    @Override
    public List<Order> findAll(Session session) {
        return session.createCriteria(Order.class).list();
    }

    @Override
    public Order update(Session session, Order entity) {
        if (entity.getId() == null)
            throw new RuntimeException("Id cannot be null in update method");
        session.update(entity);
        return entity;
    }

    @Override
    public Order save(Session session, Order user) {
        Long id = (Long) session.save(user);
        user.setId(id);
        return user;
    }

    @Override
    public void delete(Session session, Order entity) {
        session.delete(entity);
    }
}
