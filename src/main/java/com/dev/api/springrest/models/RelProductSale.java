package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rel_prod_sale")
public class RelProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rel_id")
    private long idRel;
    @Column(name = "rel_prod_id")
    private long idProd;
    @Column(name = "rel_sale_id")
    private long idSale;

    public RelProductSale() {
    }

    public RelProductSale(long idRel, long idProd, long idSale) {
        this.idRel = idRel;
        this.idProd = idProd;
        this.idSale = idSale;
    }

    @ManyToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleTable sale;
}
