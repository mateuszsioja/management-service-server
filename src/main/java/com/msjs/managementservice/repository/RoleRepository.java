package com.msjs.managementservice.repository;

import com.msjs.managementservice.model.Role;
import com.msjs.managementservice.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleType(RoleType roleType);
}