package com.dev.api.springrest.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String username;
    private String password;
}