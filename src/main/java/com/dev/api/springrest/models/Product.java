package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;
    @Column(name = "prod_name", unique = true, nullable = false)
    private String name;
    @Column(name = "prod_unit_value", nullable = false)
    private Double unitaryValue;
    @Column(name = "prod_description", nullable = false)
    private String description;
    @Column(name = "prod_exp_date", nullable = false)
    private Date expirationDate;
    @Column(name = "prod_quantity", nullable = false)
    private int quantity;

    //RELATIONSHIP CATEGORY
    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    //RELATIONSHIP PRODUCT to SALE
    @OneToMany(mappedBy = "product")
    private Set<RelProductSale> productSales;

    //RELATIONSHIP EMPLOYEE
    @ManyToOne
    private Employee employee;

    public Product() {
    }

    public Product(Long id, String name, Double unitaryValue, String description, Date expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.unitaryValue = unitaryValue;
        this.description = description;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
}
