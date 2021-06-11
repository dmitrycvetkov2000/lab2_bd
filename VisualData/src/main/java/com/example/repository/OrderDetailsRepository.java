package com.example.repository;

import com.example.model.Order;
import com.example.model.OrderDetails;
import org.hibernate.Session;

import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;

public class OrderDetailsRepository implements Repository<OrderDetails> {
    public OrderDetailsRepository() {

    }

    public List<OrderDetails> getByOrder(Session session,Order order){
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<OrderDetails> criteria = builder.createQuery(OrderDetails.class);
        Root<OrderDetails> root = criteria.from(OrderDetails.class);
        criteria.select(root).where(builder.equal(root.get("orderDetailsPK").get("orderId"),order.getId()));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public OrderDetails get(Session session, Long id) {
        return session.get(OrderDetails.class, id);
    }

    @Override
    public int count(Session session) {
        return ((BigInteger) session.createSQLQuery("SELECT COUNT(id) FROM " + OrderDetails.class.getAnnotation(Table.class).name()).uniqueResult()).intValue();
    }

    @Override
    public List<OrderDetails> findAll(Session session) {
        return session.createCriteria(OrderDetails.class).list();
    }

    @Override
    public OrderDetails update(Session session, OrderDetails entity) {
        session.update(entity);
        return entity;
    }

    @Override
    public OrderDetails save(Session session, OrderDetails entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(Session session, OrderDetails entity) {
        session.delete(entity);
    }
}
