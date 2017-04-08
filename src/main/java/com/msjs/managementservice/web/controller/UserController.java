package com.msjs.managementservice.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.msjs.managementservice.model.User;
import com.msjs.managementservice.service.TaskService;
import com.msjs.managementservice.service.UserService;
import com.msjs.managementservice.web.dto.PatchRoleDto;
import com.msjs.managementservice.web.dto.TaskDto;
import com.msjs.managementservice.web.dto.UserDto;
import com.msjs.managementservice.web.dto.ViewJson;
import com.msjs.managementservice.web.dto.mapper.TaskMapper;
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
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, TaskService taskService, TaskMapper taskMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createUser(@Valid @RequestBody @JsonView(ViewJson.InputUser.class) final UserDto userDto) {
        User user = userService.saveUser(userMapper.mapToEntity(userDto));
        return ResponseEntity.created(getLocationURI(user.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(ID_PATH_PARAM)
    public ResponseEntity changeUserRole(@PathVariable("id") Long id,
                                         @Valid @RequestBody PatchRoleDto patchRoleDto,
                                         HttpServletResponse response) {
        User user = userService.changeUserRole(patchRoleDto.getRole(), id);
        response.setHeader(LOCATION_HEADER, user.getId().toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(USER_TASKS)
    public ResponseEntity<List<TaskDto>> getUserTasks(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(taskMapper.mapToDtoList(taskService.getUserTasks(userId)),
                HttpStatus.OK);
    }
}
