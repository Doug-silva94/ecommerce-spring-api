package com.dev.api.springrest.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "sale")
    private Set<ProductSale> productSales;

    @OneToOne
    private Client client;

    public Sale() {
    }

    public Sale(long id, Date date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;

    }

}
