package com.msjs.managementservice.core.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Immutable
@Table(name = "person")
public class UsersUniqueFieldsView {
    @Id
    private Long id;
    private String username;
    private String email;
}
