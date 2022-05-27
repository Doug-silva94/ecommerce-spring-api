package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", unique = true)
    private Long id;
    @Column(name = "employee_name")
    @NotNull(message = "Name may not be null")
    @Size(min = 10, max = 50)
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


}
