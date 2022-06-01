package com.dev.api.springrest.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;
    private String description;

    public CategoryDto() {}

    public CategoryDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}