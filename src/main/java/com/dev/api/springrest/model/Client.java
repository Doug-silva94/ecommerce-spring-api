package com.dev.api.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false)
    @Size(min = 5, max = 15)
    private String userName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cpf", unique = true)
    @Size(max = 14)
    private String cpf;
    @Column(name = "birth")
    private Date birthDate;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "tel", nullable = false)
    private String telephone;

    @OneToOne(mappedBy = "client")
    private Sale sale;


}
