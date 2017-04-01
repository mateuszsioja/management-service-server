package com.msjs.managementservice.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(ViewJson.InputUser.class)
    private String username;
    @JsonView(ViewJson.InputUser.class)
    private String password;
    @JsonView(ViewJson.InputUser.class)
    private String firstName;
    @JsonView(ViewJson.InputUser.class)
    private String lastName;
    @JsonView(ViewJson.InputUser.class)
    private String email;
    private List<RoleDto> roles;
}
