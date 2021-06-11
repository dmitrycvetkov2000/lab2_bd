package com.example.model;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name="client")
public class Client implements Serializable {
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

    public Client() {
    }

    public Client(String name, String middleName, String lastName, String address, String phone) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }


}