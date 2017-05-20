package com.msjs.managementservice.core.repository;

import com.msjs.managementservice.core.model.ShortUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jakub on 20.05.2017.
 */
public interface ShortUserRepository extends JpaRepository<ShortUser, Long> {
}
