package com.msjs.managementservice.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.msjs.managementservice.model.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jakub on 29.03.2017.
 */
@EqualsAndHashCode
@Getter
@Setter
public class UserDto {
    private Long id;

    @JsonView({ViewJson.InputUser.class, ViewJson.UpdateUser.class})
    private String username;

    @JsonView({ViewJson.InputUser.class, ViewJson.UpdateUser.class})
    private String password;

    @JsonView({ViewJson.InputUser.class, ViewJson.UpdateUser.class})
    private String firstName;

    @JsonView({ViewJson.InputUser.class, ViewJson.UpdateUser.class})
    private String lastName;

    @JsonView({ViewJson.InputUser.class, ViewJson.UpdateUser.class})
    private String email;

    @JsonView(ViewJson.UpdateUser.class)
    private Role role;
}
