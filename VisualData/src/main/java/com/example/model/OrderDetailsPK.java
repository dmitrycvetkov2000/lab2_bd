package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderDetailsPK implements Serializable {
    @Column(name="product_id", nullable = false)
    private Long productId;
    @Column(name="order_id", nullable = false)
    private Long orderId;
}
