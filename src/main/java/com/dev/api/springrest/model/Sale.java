package com.dev.api.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_table")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "service_type")
    private String serviceType;
    @Column(name = "nfe")
    private String nfe;

    @OneToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    private Client client;

}