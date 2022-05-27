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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;
    @Column(name = "cat_name", unique = true)
    @NotNull(message = "Name may not be null")
    @Size(min = 3, max = 20)
    private String name;
    @Column(name = "cat_desc")
    @NotBlank(message = "Description may not be blank")
    @Size(min = 5, max = 20)
    private String description;

    //RELATIONSHIP PRODUCTS
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
