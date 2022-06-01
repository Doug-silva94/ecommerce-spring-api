package com.dev.api.springrest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;
    private String userName;
    private String email;
    private String cpf;
    private Date birthDate;
    private String address;
    private String telephone;

}
