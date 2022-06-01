package com.dev.api.springrest.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;
    private String cpf;
    
    public EmployeeDto() {}
    
    public EmployeeDto(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }
    
}