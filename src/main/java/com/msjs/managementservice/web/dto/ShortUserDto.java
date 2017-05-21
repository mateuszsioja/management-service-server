package com.msjs.managementservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jakub on 20.05.2017.
 */
@AllArgsConstructor
@Getter
public class ShortUserDto {
    private final Long id;
    private final String fullName;
    private final String username;
}
