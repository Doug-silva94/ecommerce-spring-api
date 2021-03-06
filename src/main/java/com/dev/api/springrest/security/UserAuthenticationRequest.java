package com.dev.api.springrest.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationRequest {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

}
