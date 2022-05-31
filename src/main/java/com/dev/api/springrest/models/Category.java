package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
