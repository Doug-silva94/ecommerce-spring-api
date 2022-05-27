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
    @Column(name = "sale_id")
    private long id;
    @Column(name = "sale_date")
    private Date date;
    @Column(name = "sale_quantity")
    private int quantity;
    @Column(name = "sale_value")
    private double value;

    //RELATIONSHIP SALE to PRODUCT
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<RelProductSale> productSales;

    //RELATIONSHIP SALE to CLIENTS
//    @OneToMany(mappedBy = "saleTable", cascade = CascadeType.ALL)
//    @JoinColumn(name = "client")
//    private List<Client> clients;


    public SaleTable() {
    }

    public SaleTable(long id, Date date, double value) {
        this.id = id;
        this.date = date;
        this.value = value;

    }
}
