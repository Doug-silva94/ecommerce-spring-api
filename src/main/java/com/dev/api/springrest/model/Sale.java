package com.dev.api.springrest.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "sale")
    private Set<ProductSale> productSales;

    @OneToOne
    private Client client;

}
