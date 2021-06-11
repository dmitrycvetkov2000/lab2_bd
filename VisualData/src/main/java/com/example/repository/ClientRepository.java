package com.example.repository;

import com.example.model.Client;
import org.hibernate.Session;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class ClientRepository implements Repository<Client> {
    public ClientRepository() {

    }

    @Override
    public Client get(Session session, Long id) {
        return session.get(Client.class, id);
    }

    @Override
    public int count(Session session) {
        return ((BigInteger) session.createSQLQuery("SELECT COUNT(id) FROM " + Client.class.getAnnotation(Table.class).name()).uniqueResult()).intValue();
    }

    @Override
    public List<Client> findAll(Session session) {
        return session.createCriteria(Client.class).list();
    }

    @Override
    public Client update(Session session, Client entity) {
        if (entity.getId() == null)
            throw new RuntimeException("Id cannot be null in update method");
        session.update(entity);
        return entity;
    }

    @Override
    public Client save(Session session, Client user) {
        Long id = (Long) session.save(user);
        user.setId(id);
        return user;
    }

    @Override
    public void delete(Session session, Client entity) {
        session.delete(entity);
    }

}
