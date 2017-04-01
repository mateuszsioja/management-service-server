package com.msjs.managementservice.dto.mapper;

import com.msjs.managementservice.dto.UserDto;
import com.msjs.managementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by jakub on 30.03.2017.
 */

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream().map(roleMapper::mapToDto).collect(Collectors.toList()));
        return dto;
    }

    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
