package com.example.gui;

import com.example.model.Product;
import com.example.repository.ProductRepository;

public class ProductTableModel extends AbstractTableModel<Product> {

    public ProductTableModel() {
        super(new ProductRepository());
        update();
    }

    @Override
    protected Class<Product> getClazz() {
        return Product.class;
    }
}
