package com.msjs.managementservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static com.msjs.managementservice.model.Constants.*;

/**
 * Created by jakub on 29.03.2017.
 */
@EqualsAndHashCode
@Getter
@Setter
@Entity(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
