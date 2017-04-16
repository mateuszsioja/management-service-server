package com.msjs.managementservice.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersUniqueFieldsDto {
    private List<String> usernames = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
}
