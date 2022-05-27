package com.dev.api.springrest.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;
    @Column(name = "prod_name", unique = true, nullable = false)
    private String name;
    @Column(name = "prod_unit_value", nullable = false)
    private Double unitaryValue;
    @Column(name = "prod_description", nullable = false)
    private String description;
    @Column(name = "prod_exp_date", nullable = false)
    private Date expirationDate;
    @Column(name = "prod_quantity", nullable = false)
    private int quantity;

    //RELATIONSHIP CATEGORY
    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    //RELATIONSHIP PRODUCT to SALE
    @OneToMany(mappedBy = "product")
    private Set<RelProductSale> productSales;

    //RELATIONSHIP EMPLOYEE
    @ManyToOne
    private Employee employee;

    public Product() {
    }

    public Product(Long id, String name, Double unitaryValue, String description, Date expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.unitaryValue = unitaryValue;
        this.description = description;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(Double unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<RelProductSale> getProductSales() {
		return productSales;
	}

	public void setProductSales(Set<RelProductSale> productSales) {
		this.productSales = productSales;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
    
}
