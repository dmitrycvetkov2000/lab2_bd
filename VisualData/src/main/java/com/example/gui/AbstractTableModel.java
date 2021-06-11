package com.example.gui;

import com.example.config.HibernateConfig;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.repository.Repository;
import com.example.shared.StringDataTypeConverter;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractTableModel<T> implements TableModel {
    protected String[] columns = Arrays.stream(getClazz().getDeclaredFields()).map(Field::getName).toArray(String[]::new);
    protected Repository<T> repository;
    protected List<T> data;

    public AbstractTableModel(Repository<T> repository) {
        this.repository = repository;
        update();
    }

    protected abstract Class<T> getClazz();

    public void update() {
        Session session = HibernateConfig.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        data = repository.findAll(session);
        transaction.commit();
        session.close();
    }

    public void removeRow(int index) {
        Session session = HibernateConfig.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        repository.delete(session, data.get(index));
        transaction.commit();
        session.close();
        data.remove(index);
    }

    public void addRow(T entity) {
        Session session = HibernateConfig.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        entity = repository.save(session, entity);
        data.add(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns[columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @SneakyThrows
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field[] fields = getClazz().getDeclaredFields();
        Field field = fields[columnIndex];
        field.setAccessible(true);
        return field.get(data.get(rowIndex));
    }

    @SneakyThrows
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Field[] fields = getClazz().getDeclaredFields();
        Field field = fields[columnIndex];
        field.setAccessible(true);
        T object = data.get(rowIndex);
        field.set(object, new StringDataTypeConverter().convertToObjectByType(aValue.toString(), field.getType()));

        Session session = HibernateConfig.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        object = repository.update(session, object);
        transaction.commit();
        session.close();
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
