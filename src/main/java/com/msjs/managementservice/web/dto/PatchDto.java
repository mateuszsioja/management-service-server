package com.msjs.managementservice.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PatchDto {
    @NotNull
    private String targetField;
    @NotNull
    private String value;
}
