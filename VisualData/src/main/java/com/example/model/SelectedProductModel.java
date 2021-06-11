package com.example.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
public class SelectedProductModel implements Serializable {

    private Long id;
    private String name;
    private Float price;
    private Integer quantity;


    public SelectedProductModel() {
    }

    public SelectedProductModel(Product product, Integer quantity) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectedProductModel)) return false;
        SelectedProductModel product = (SelectedProductModel) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}