package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ShopCartDTO {

    private long id;
    private Date date;
    private double value;
    private long idClient;
    private long idProd;

    public ShopCartDTO() {}

    public ShopCartDTO(long id, Date date, double value, long idClient, long idProd) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.idClient = idClient;
        this.idProd = idProd;
    }
}
