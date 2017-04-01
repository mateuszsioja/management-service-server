package com.msjs.managementservice.repository;

import com.msjs.managementservice.model.Role;
import com.msjs.managementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    List<User> findAllByRole(Role role);
}
