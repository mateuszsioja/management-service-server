package com.msjs.managementservice.web.dto.mapper;

import com.msjs.managementservice.model.User;
import com.msjs.managementservice.web.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 30.03.2017.
 */

@Component
public class UserMapper {

    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

    public List<UserDto> mapToDtoList(List<User> users) {
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<User> mapToEntityList(List<UserDto> userDtos) {
        return userDtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
