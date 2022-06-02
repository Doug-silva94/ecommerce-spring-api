package com.dev.api.springrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private Date date;
    private double value;
    private long idClient;
    private long idProd;

}
