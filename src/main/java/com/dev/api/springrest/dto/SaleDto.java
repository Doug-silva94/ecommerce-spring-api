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
public class SaleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date date;
    private Double price;
    private Integer quantity;
    private Long idClient;
    private Long idProd;
    private String nfe;
    private String serviceType;
}
