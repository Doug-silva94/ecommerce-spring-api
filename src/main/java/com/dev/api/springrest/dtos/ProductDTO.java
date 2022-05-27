package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double unitaryValue;
    private String description;
    private long catId;
    private Date expirationDate;
    private int quantity;

    public ProductDTO() {
    }
    public ProductDTO(Long id, String name, Double unitaryValue, String description, long catId, Date expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.unitaryValue = unitaryValue;
        this.description = description;
        this.catId = catId;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
}