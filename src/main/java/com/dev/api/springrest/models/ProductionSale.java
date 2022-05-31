package com.dev.api.springrest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
<<<<<<< HEAD:src/main/java/com/dev/api/springrest/models/RelProductSale.java
    @JoinColumn(name = "sale_id")
    private Sale sale;
=======
    @JoinColumn(name = "sale_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleTable sale;
>>>>>>> 6e2ed1bf7eb133be02a242addd9a4243a1aaf825:src/main/java/com/dev/api/springrest/models/ProductionSale.java
}
