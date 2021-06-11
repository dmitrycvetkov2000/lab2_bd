package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity
@Immutable
@Table(name="client_order_view")
public class ClientOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="middle_name", nullable = false)
    private String middleName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name = "date",insertable = false)
    private OffsetDateTime date;

    @Column(name = "sum",insertable = false)
    private Float sum;
}
