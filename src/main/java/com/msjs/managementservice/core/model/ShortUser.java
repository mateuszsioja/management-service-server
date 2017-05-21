package com.msjs.managementservice.core.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 20.05.2017.
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "person")
public class ShortUser {

    @Id
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
