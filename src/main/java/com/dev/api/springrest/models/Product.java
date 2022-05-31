package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private Date expirationDate;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id",  nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<ProductionSale> productSales;

    @ManyToOne
    private Employee employee;

    public Product() {
    }

    public Product(Long id, String name, Double price, String description, Date expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
}
