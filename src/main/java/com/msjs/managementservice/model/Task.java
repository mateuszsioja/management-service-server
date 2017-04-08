package com.msjs.managementservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static com.msjs.managementservice.model.Constants.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = SIZE_2, max = SIZE_25)
    private String type;

    @Size(min = SIZE_2, max = SIZE_25)
    private String summary;

    @Size(min = SIZE_2, max = SIZE_25)
    private String priority;

    @Size(min = SIZE_2, max = SIZE_25)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}