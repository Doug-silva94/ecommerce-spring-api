package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SaleDTO {

    private long id;
    private Date date;
    private Double price;
    private Integer quantity;
    private long idClient;
    private long idProd;

    public SaleDTO() {}

    public SaleDTO(long id, Date date, double price, long idClient, long idProd) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.idClient = idClient;
        this.idProd = idProd;
    }
    
}
