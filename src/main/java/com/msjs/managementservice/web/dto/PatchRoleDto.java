package com.msjs.managementservice.web.dto;

import com.msjs.managementservice.core.model.validation.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchRoleDto {
    @RoleType
    String role;
}
