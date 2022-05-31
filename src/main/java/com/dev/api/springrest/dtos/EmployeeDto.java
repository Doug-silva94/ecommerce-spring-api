package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String name;
    private String cpf;
    public EmployeeDto() {
    }
    public EmployeeDto(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }
}
