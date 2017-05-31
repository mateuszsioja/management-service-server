package com.msjs.managementservice.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 29.03.2017.
 */
@EqualsAndHashCode
@Getter
@Setter
@Entity(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = Constants.SIZE_2)
    private String password;

    @NotNull
    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    private String firstName;

    @NotNull
    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    private boolean credentialsNotExpired;

    @Transient
    public void assignTask(final Task task) {
        tasks.add(task);
        task.setUser(this);
    }
}
