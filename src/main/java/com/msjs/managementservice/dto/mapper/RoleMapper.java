package com.msjs.managementservice.dto.mapper;

import com.msjs.managementservice.dto.RoleDto;
import com.msjs.managementservice.model.Role;
import org.springframework.stereotype.Component;

/**
 * Created by jakub on 01.04.2017.
 */
@Component
public class RoleMapper {

    public RoleDto mapToDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setRoleType(role.getRoleType());
        return dto;
    }
}
