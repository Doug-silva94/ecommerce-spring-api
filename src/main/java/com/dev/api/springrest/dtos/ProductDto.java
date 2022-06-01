package com.dev.api.springrest.dtos;

import com.dev.api.springrest.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;
    private Double unitaryValue;
    private String description;
    private long catId;
    private Date expirationDate;
    private int quantity;

    public ProductDto() {}
    
    public ProductDto(Long id, String name, Double unitaryValue, String description, long catId, Date expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.unitaryValue = unitaryValue;
        this.description = description;
        this.catId = catId;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public ProductDto(Product product) {}

}