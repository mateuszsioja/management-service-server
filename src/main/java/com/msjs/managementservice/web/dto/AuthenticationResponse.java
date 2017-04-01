package com.msjs.managementservice.web.dto;

import lombok.Getter;

import java.io.Serializable;

/**
 * Created by jakub on 01.04.2017.
 */
@Getter
public class AuthenticationResponse implements Serializable{

    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
