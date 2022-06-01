package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ShopCartDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
    private Date date;
    private double value;
    private long idClient;
    private long idProd;

    public ShopCartDto() {}

    public ShopCartDto(long id, Date date, double value, long idClient, long idProd) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.idClient = idClient;
        this.idProd = idProd;
    }
    
}
