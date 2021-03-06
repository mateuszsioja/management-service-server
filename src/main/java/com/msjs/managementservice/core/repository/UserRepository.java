package com.msjs.managementservice.core.repository;

import com.msjs.managementservice.core.model.Role;
import com.msjs.managementservice.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    List<User> findAllByRole(Role role);

    User findUserByUsername(String username);
}
