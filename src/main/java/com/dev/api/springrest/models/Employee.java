package com.dev.api.springrest.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", unique = true)
    private Long id;
    @Column(name = "employee_name", nullable = false)
    private String name;
    @Column(name = "employee_cpf", unique = true)
    @NotBlank(message = "CPF may not be blank")
    @Size(max = 14)
    private String cpf;

    //RELATIONSHIP PRODUCTS
    @OneToMany(mappedBy = "employee")
    private List<Product> product;

    public Employee() {
    }

    public Employee(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
    
    


}
