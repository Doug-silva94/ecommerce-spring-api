package com.dev.api.springrest.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticationResponse {

    private final String token;


}
