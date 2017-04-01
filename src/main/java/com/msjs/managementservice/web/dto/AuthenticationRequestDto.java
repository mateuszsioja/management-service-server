package com.msjs.managementservice.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jakub on 01.04.2017.
 */
@Getter
@Setter
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
