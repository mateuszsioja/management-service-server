package com.msjs.managementservice.core.service;

import com.msjs.managementservice.core.model.Task;
import com.msjs.managementservice.core.model.User;
import com.msjs.managementservice.core.repository.TaskRepository;
import com.msjs.managementservice.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findTasksForSpecificUser(userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, Long id) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(taskRepository.findOne(id));
    }

    public void assignTaskToUser(Long taskId, Long userId) {
//        Task task = taskRepository.findOne(taskId);
//        task.setUser(userRepository.getOne(userId));
//        taskRepository.save(task);
        final User user = userRepository.findOne(userId);
        user.assignTask(taskRepository.findOne(taskId));
        userRepository.save(user);
    }
}
