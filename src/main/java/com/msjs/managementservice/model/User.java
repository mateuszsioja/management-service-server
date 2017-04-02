package com.msjs.managementservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.msjs.managementservice.model.Constants.SIZE_2;
import static com.msjs.managementservice.model.Constants.SIZE_25;

/**
 * Created by jakub on 29.03.2017.
 */
@EqualsAndHashCode
@Getter
@Setter
@Entity(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = SIZE_2, max = SIZE_25)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = SIZE_2)
    private String password;

    @NotNull
    @Size(min = SIZE_2, max = SIZE_25)
    private String firstName;

    @NotNull
    @Size(min = SIZE_2, max = SIZE_25)
    private String lastName;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean credentialsNotExpired;
}
