package com.dev.api.springrest.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthenticationResponse {

    private final String token;

}
