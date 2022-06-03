package com.dev.api.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "username", length = 10, nullable = false)
    private String userName;
    @Column(name = "email", length = 70, nullable = false)
    private String email;
    @Column(name = "cpf", length = 16, unique = true)
    private String cpf;
    @Column(name = "birth", length = 16)
    private Date birthDate;
    @Column(name = "address", length = 100, nullable = false)
    private String address;
    @Column(name = "tel", length = 16, nullable = false)
    private String telephone;

    @OneToOne(mappedBy = "client")
    private Sale sale;


}
