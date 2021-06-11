package com.example.gui;

import com.example.config.HibernateConfig;
import com.example.model.ClientOrder;
import com.example.model.Order;
import com.example.model.OrderDetails;
import com.example.model.Product;
import com.example.repository.ClientOrderRepository;
import com.example.repository.OrderDetailsRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientOrderTableModel extends AbstractTableModel<ClientOrder> {
    private final OrderRepository orderRepository = new OrderRepository();
    private final OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
    public ClientOrderTableModel() {
        super(new ClientOrderRepository());
        update();
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    protected Class<ClientOrder> getClazz() {
        return ClientOrder.class;
    }

    @Override
    public void removeRow(int index) {
        Session session = HibernateConfig.sessionFactory.openSession();
        Order order = orderRepository.get(session,data.get(index).getId());
        List<OrderDetails> orderDetailsList = orderDetailsRepository.getByOrder(session,order);

        Transaction transaction = session.beginTransaction();
        orderDetailsList.forEach(e->orderDetailsRepository.delete(session,e));
        orderRepository.delete(session, order);
        transaction.commit();

        session.close();
        data.remove(index);
    }
}
