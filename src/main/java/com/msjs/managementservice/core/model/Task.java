package com.msjs.managementservice.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    private String type;

    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    private String summary;

    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    private String priority;

    @Size(min = Constants.SIZE_2, max = Constants.SIZE_25)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}