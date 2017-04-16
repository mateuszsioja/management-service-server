package com.msjs.managementservice.web.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

import static com.msjs.managementservice.core.model.Constants.SIZE_2;
import static com.msjs.managementservice.core.model.Constants.SIZE_25;

@Getter
@Setter
@EqualsAndHashCode
public class TaskDto {

    private Long id;

    @Size(min = SIZE_2, max = SIZE_25)
    private String type;

    @Size(min = SIZE_2, max = SIZE_25)
    private String summary;

    @Size(min = SIZE_2, max = SIZE_25)
    private String priority;

    @Size(min = SIZE_2, max = SIZE_25)
    private String status;

    private Long userId;
}
