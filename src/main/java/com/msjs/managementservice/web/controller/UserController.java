package com.msjs.managementservice.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.msjs.managementservice.core.model.ShortUser;
import com.msjs.managementservice.core.model.User;
import com.msjs.managementservice.core.model.UsersUniqueFieldsView;
import com.msjs.managementservice.core.repository.ShortUserRepository;
import com.msjs.managementservice.core.repository.UserRepository;
import com.msjs.managementservice.core.repository.UsersUniqueFieldsRepository;
import com.msjs.managementservice.core.service.TaskService;
import com.msjs.managementservice.core.service.UserService;
import com.msjs.managementservice.web.dto.*;
import com.msjs.managementservice.web.dto.mapper.ShortUserMapper;
import com.msjs.managementservice.web.dto.mapper.TaskMapper;
import com.msjs.managementservice.web.dto.mapper.UserMapper;
import com.msjs.managementservice.web.dto.mapper.UsersUniqueFieldsMapper;
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
@CrossOrigin
@RestController
@RequestMapping(USERS)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final ShortUserRepository shortUserRepository;
    private final ShortUserMapper shortUserMapper;
    private final UsersUniqueFieldsMapper usersUniqueFieldsMapper;
    private final UsersUniqueFieldsRepository usersUniqueFieldsRepository;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, TaskService taskService, TaskMapper taskMapper, UserRepository userRepository, ShortUserRepository shortUserRepository, ShortUserMapper shortUserMapper, UsersUniqueFieldsMapper usersUniqueFieldsMapper, UsersUniqueFieldsRepository usersUniqueFieldsRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.shortUserRepository = shortUserRepository;
        this.shortUserMapper = shortUserMapper;
        this.usersUniqueFieldsMapper = usersUniqueFieldsMapper;
        this.usersUniqueFieldsRepository = usersUniqueFieldsRepository;
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
    public ResponseEntity<Void> createUser(@Valid @RequestBody @JsonView(ViewJson.InputUser.class) UserDto userDto) {
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

    @GetMapping(UNIQUE_FIELDS)
    public ResponseEntity<UsersUniqueFieldsDto> getUsersUniqueFields() {
        List<UsersUniqueFieldsView> views = usersUniqueFieldsRepository.findAll();
        return new ResponseEntity<>(usersUniqueFieldsMapper.toDto(views), HttpStatus.OK);
    }

    @GetMapping(SHORT)
    public ResponseEntity<List<ShortUserDto>> getShortUsers() {
        List<ShortUser> shortUsers = shortUserRepository.findAll();
        return new ResponseEntity<>(shortUserMapper.mapToDto(shortUsers), HttpStatus.OK);
    }
}
