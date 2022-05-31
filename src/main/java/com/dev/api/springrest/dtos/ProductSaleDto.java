package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductSaleDto {

    private long id;
    private long idProd;
    private long idSale;

    public ProductSaleDto(Long id, long idProd, long idSale) {
        this.id = id;
        this.idProd = idProd;
        this.idSale = idSale;
    }
}
