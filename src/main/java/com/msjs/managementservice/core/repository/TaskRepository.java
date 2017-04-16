package com.msjs.managementservice.core.repository;

import com.msjs.managementservice.core.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t join t.user u where u.id = :userId")
    List<Task> findTasksForSpecificUser(@Param("userId") Long userId);
}
