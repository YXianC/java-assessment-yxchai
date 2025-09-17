package com.yxchai.javaassessment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "customer", schema = "TESTDB")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 6786316204482490362L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String name;

    @Column(name = "customer_email", length = 100)
    private String email;
}
