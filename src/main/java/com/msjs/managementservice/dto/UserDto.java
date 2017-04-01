package com.msjs.managementservice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by jakub on 29.03.2017.
 */
@EqualsAndHashCode
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleDto> roles;
}
