package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product_sale")
public class ProductionSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idRel;
    @Column(name = "prod_id")
    private long idProd;
    @Column(name = "sale_id")
    private long idSale;

    public ProductionSale() {
    }

    public ProductionSale(long idRel, long idProd, long idSale) {
        this.idRel = idRel;
        this.idProd = idProd;
        this.idSale = idSale;
    }

    @ManyToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleTable sale;
}
