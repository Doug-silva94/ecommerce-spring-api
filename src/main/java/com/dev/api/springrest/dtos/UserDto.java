package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String password;
}
