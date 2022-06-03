package com.dev.api.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "service_type", length = 20, nullable = false)
    private String serviceType;
    @Column(name = "nfe", nullable = false)
    private String nfe;

    @OneToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    private Client client;

}