package com.dev.api.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",  length = 50,  nullable = false)
    private String name;
    @Column(name = "description",  length = 100,  nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
    @ManyToOne
    private Employee employee;


}