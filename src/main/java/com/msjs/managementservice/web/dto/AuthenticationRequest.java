package com.msjs.managementservice.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jakub on 01.04.2017.
 */
@Getter
@Setter
public class AuthenticationRequest {
    private String username;
    private String password;
}
