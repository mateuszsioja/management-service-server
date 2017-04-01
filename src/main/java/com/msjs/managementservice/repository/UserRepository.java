package com.msjs.managementservice.repository;

import com.msjs.managementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
