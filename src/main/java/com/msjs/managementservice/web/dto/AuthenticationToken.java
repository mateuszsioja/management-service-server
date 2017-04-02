package com.msjs.managementservice.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jakub on 01.04.2017.
 */
@Getter
@Setter
public class AuthenticationToken implements Serializable{

    private String token;

    public AuthenticationToken(String token) {
        this.token = token;
    }
}
