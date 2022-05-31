package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sale_table")
public class SaleTable {
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
    private Set<ProductionSale> productSales;

    @OneToOne
    private Client client;

    public SaleTable() {
    }

    public SaleTable(long id, Date date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;

    }
}
