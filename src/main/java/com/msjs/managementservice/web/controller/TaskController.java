package com.msjs.managementservice.web.controller;

import com.msjs.managementservice.core.model.Task;
import com.msjs.managementservice.core.service.TaskService;
import com.msjs.managementservice.web.dto.PatchDto;
import com.msjs.managementservice.web.dto.TaskDto;
import com.msjs.managementservice.web.dto.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.msjs.managementservice.web.controller.ApiUrl.*;

@CrossOrigin
@RestController
@RequestMapping(TASKS)
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskMapper.mapToDtoList(taskService.getAllTasks()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskService.createTask(taskMapper.mapToEntity(taskDto));
        return ResponseEntity.created(getLocationURI(task.getId())).build();
    }

    @PutMapping(ID_PATH_PARAM)
    public ResponseEntity updateTask(@RequestBody @Valid TaskDto taskDto,
                                     @PathVariable("id") Long id,
                                     HttpServletResponse response) {
        Task task = taskService.updateTask(taskMapper.mapToEntity(taskDto), id);
        response.setHeader(LOCATION_HEADER, task.getId().toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(ID_PATH_PARAM)
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(ID_PATH_PARAM)
    public ResponseEntity assignTaskToUser(@PathVariable("id") Long id,
                                           @RequestBody @Valid PatchDto patchDto) {
        taskService.assignTaskToUser(id, Long.parseLong(patchDto.getValue()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
