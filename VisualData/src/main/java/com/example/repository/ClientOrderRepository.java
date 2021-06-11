package com.example.repository;

import com.example.model.ClientOrder;
import com.example.model.Order;
import org.hibernate.Session;

import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

public class ClientOrderRepository implements Repository<ClientOrder> {
    public ClientOrderRepository() {

    }

    @Override
    public ClientOrder get(Session session, Long id) {
        return session.get(ClientOrder.class, id);
    }

    @Override
    public int count(Session session) {
        return ((BigInteger) session.createSQLQuery("SELECT COUNT(id) FROM "  + ClientOrder.class.getAnnotation(Table.class).name()).uniqueResult()).intValue();
    }

    @Override
    public List<ClientOrder> findAll(Session session) {
        return session.createCriteria(ClientOrder.class).list();
    }

    @Override
    public ClientOrder update(Session session, ClientOrder entity) {
        if (entity.getId() == null)
            throw new RuntimeException("Id cannot be null in update method");
        session.update(entity);
        return entity;
    }

    @Override
    public ClientOrder save(Session session, ClientOrder entity) {
        Long id = (Long) session.save(entity);
        entity.setId(id);
        return entity;
    }

    @Override
    public void delete(Session session, ClientOrder entity) {
        session.delete(entity);
    }
}
