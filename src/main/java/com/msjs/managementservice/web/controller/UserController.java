package com.msjs.managementservice.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.msjs.managementservice.model.User;
import com.msjs.managementservice.service.UserService;
import com.msjs.managementservice.web.dto.UserDto;
import com.msjs.managementservice.web.dto.ViewJson;
import com.msjs.managementservice.web.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.msjs.managementservice.web.controller.ApiUrl.USERS;
import static com.msjs.managementservice.web.controller.ApiUrl.getLocationURI;

/**
 * Created by jakub on 29.03.2017.
 */
@RestController
@RequestMapping(USERS)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createUser(@Valid @RequestBody @JsonView(ViewJson.InputUser.class) final UserDto userDto) {
        User user = userService.saveUser(userMapper.mapToEntity(userDto));
        return ResponseEntity.created(getLocationURI(user.getId())).build();
    }
}
