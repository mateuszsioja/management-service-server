package com.msjs.managementservice.web.dto.mapper;

import com.msjs.managementservice.core.model.Task;
import com.msjs.managementservice.core.repository.UserRepository;
import com.msjs.managementservice.web.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    private UserRepository userRepository;

    public TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setSummary(task.getSummary());
        taskDto.setType(task.getType());
        taskDto.setStatus(task.getStatus());
        taskDto.setPriority(task.getPriority());
        if (task.getUser() != null) {
            taskDto.setUserId(task.getUser().getId());
        }
        return taskDto;
    }

    public Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setSummary(taskDto.getSummary());
        task.setType(taskDto.getType());
        task.setStatus(taskDto.getStatus());
        task.setPriority(taskDto.getPriority());
        if (taskDto.getUserId() != null) {
            task.setUser(userRepository.findOne(taskDto.getUserId()));
        }
        return task;
    }

    public List<TaskDto> mapToDtoList(List<Task> tasks) {
        return tasks.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<Task> mapToEntityList(List<TaskDto> taskDtos) {
        return taskDtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
