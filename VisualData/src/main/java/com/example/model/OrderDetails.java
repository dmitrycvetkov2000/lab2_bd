package com.example.model;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name="order_details")
public class OrderDetails implements Serializable {
    @EmbeddedId
    private OrderDetailsPK orderDetailsPK;
    private Integer quantity;

    public OrderDetails(){

    }
    public OrderDetails(OrderDetailsPK orderDetailsPK, Integer quantity) {
        this.orderDetailsPK = orderDetailsPK;
        this.quantity = quantity;
    }
    public OrderDetails(Long productId,Long orderId, Integer quantity) {
        this.orderDetailsPK = new OrderDetailsPK(productId,orderId);
        this.quantity = quantity;
    }
}