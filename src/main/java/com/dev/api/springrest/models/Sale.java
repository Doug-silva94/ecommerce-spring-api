package com.dev.api.springrest.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private Set<ProductionSale> productSales;

    @OneToOne
    private Client client;

    public Sale() {
    }

<<<<<<< HEAD:src/main/java/com/dev/api/springrest/models/Sale.java
    public Sale(long id, Date date, double value) {
=======
    public SaleTable(long id, Date date, double price) {
>>>>>>> 6e2ed1bf7eb133be02a242addd9a4243a1aaf825:src/main/java/com/dev/api/springrest/models/SaleTable.java
        this.id = id;
        this.date = date;
        this.price = price;

    }
}
