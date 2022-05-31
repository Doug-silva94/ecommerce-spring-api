package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String cpf;
    private Date birthDate;
    private String address;
    private String telephone;

    public ClientDto() {}

    public ClientDto(Long id, String name, String userName, String email, String cpf, Date birthDate, String address, String telephone) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
    }

}
