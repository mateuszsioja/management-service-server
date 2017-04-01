package com.msjs.managementservice.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.msjs.managementservice.model.User;
import com.msjs.managementservice.service.UserService;
import com.msjs.managementservice.web.dto.PathDto;
import com.msjs.managementservice.web.dto.UserDto;
import com.msjs.managementservice.web.dto.ViewJson;
import com.msjs.managementservice.web.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.msjs.managementservice.web.controller.ApiUrl.*;

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

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userMapper.mapToDtoList(userService.getAllUsers()), HttpStatus.OK);
    }

    @GetMapping(ROLE)
    public ResponseEntity<List<UserDto>> getUsersByRole(@RequestParam("type") String roleType) {
        return new ResponseEntity<>(userMapper.mapToDtoList(userService.getUsersByRole(roleType)),
                HttpStatus.OK);
    }

    @PutMapping(ID_PATH_PARAM)
    public ResponseEntity updateUser(@Valid @RequestBody @JsonView(ViewJson.UpdateUser.class) UserDto userDto,
                                     @PathVariable("id") Long id,
                                     HttpServletResponse response) {
        User user = userService.updateUser(userMapper.mapToEntity(userDto), id);
        response.setHeader(LOCATION_HEADER, user.getId().toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody @JsonView(ViewJson.InputUser.class) final UserDto userDto) {
        User user = userService.saveUser(userMapper.mapToEntity(userDto));
        return ResponseEntity.created(getLocationURI(user.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(ID_PATH_PARAM)
    public ResponseEntity changeUserRole(@PathVariable("id") Long id,
                                         @Valid @RequestBody PathDto pathDto,
                                         HttpServletResponse response) {
        User user = userService.changeUserRole(pathDto.getTargetField(), id);
        response.setHeader(LOCATION_HEADER, user.getId().toString());
        return new ResponseEntity(HttpStatus.OK);
    }
}
