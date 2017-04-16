package com.msjs.managementservice.core.repository;

import com.msjs.managementservice.core.model.UsersUniqueFieldsView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersUniqueFieldsRepository extends JpaRepository<UsersUniqueFieldsView, Long> {
}
