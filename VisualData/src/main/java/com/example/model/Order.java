package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.*;

@Data
@Entity
@Table(name = "\"order\"")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date",insertable = false)
    private OffsetDateTime date;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "sum",insertable = false)
    private Float sum;

    public Order() {
    }

    public Order(Client client) {
        this.clientId = client.getId();
    }
}