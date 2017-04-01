package com.msjs.managementservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jakub on 29.03.2017.
 */

@Getter
@Setter
public class InputUserDto {
    private String username;
    private String password;
    private String email;
}
